/*
 * @Author: Ender-Zhang 102596313+Ender-Zhang@users.noreply.github.com
 * @Date: 2022-11-04 21:59:46
 * @LastEditors: Ender-Zhang 102596313+Ender-Zhang@users.noreply.github.com
 * @LastEditTime: 2022-11-05 20:56:23
 * @FilePath: \frontend\src\app\patient-detail\patient-detail.component.ts
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {HttpService} from "../http.service";
import {forkJoin} from "rxjs";
import * as moment from 'moment';
import { ChartConfiguration } from 'chart.js';
@Component({
  selector: 'app-patient-detail',
  templateUrl: './patient-detail.component.html',
  styleUrls: ['./patient-detail.component.css']
})
export class PatientDetailComponent implements OnInit {
  title = 'Unfinished reminders in last 7 days';

  public barChartLegend = true;
  public barChartPlugins = [];

  public barChartData: ChartConfiguration<'bar'>['data'] | undefined ;

  public barChartOptions: ChartConfiguration<'bar'>['options'] = {
    responsive: false,

  };

  unfinished: any[] = [];
  constructor(private route: ActivatedRoute, private httpService: HttpService) { }

  ngOnInit(): void {
    const patientId = this.route.snapshot.paramMap.get('patientId');
    if (patientId) {
      const p = this.httpService.doctor?.patientList.find((item: any) => {
        return String(item.id) === String(patientId);
      });
      const unfinished = p.unfinishedReminderList;
      const https = unfinished.filter((item: any) => item > 0).map((id: string) => this.httpService.getRemind(id));
      let labels: string[] = [];
      let unfinishedCounts: number[] = [];
      for (let i = 0; i < 7; i ++) {
        let label = moment().subtract(i, 'days').format('YYYY-MM-DD');
        labels.push(label);
      }

      forkJoin(https).subscribe((data: any) => {
        for (let label of labels) {
          let items = data.filter((item: any) => item.reminder_date === label);
          unfinishedCounts.push(items.length);
        }
        console.log(labels, unfinishedCounts)
        this.barChartData = {
          labels: [ ...labels ],
          // label: 'Unfinished reminders in last 7 days',
          datasets: [
            { data: [ ...unfinishedCounts ], label: 'Reminders in last 7 days' }
          ]
        }
      })
    }
  }

}
