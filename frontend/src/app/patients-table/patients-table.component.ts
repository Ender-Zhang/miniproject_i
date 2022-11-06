import { Component, OnInit } from '@angular/core';
import {HttpService} from "../http.service";
import {Router} from "@angular/router";


@Component({
  selector: 'app-patients-table',
  templateUrl: './patients-table.component.html',
  styleUrls: ['./patients-table.component.css']
})
export class PatientsTableComponent implements OnInit {
  constructor(private httpService: HttpService, private router: Router) { }

  handleClickPatient(id: string) {
    this.router.navigate([`/patients/${id}`])
  }

  get patients() {
    if (!this.httpService.doctor) {
      return [];
    }
    let patients = this.httpService.doctor.patientList;
    patients.sort((prev: any, next: any) => {
      return next.unfinishedReminderList.length - prev.unfinishedReminderList.length;
    });
    return patients;
  }

  ngOnInit(): void {
    const doctorId = localStorage.getItem('doctorId');
    this.httpService.getDoctorData(String(doctorId));
  }

}
