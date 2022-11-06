import { Component, OnInit } from '@angular/core';
import {HttpService, ReminderDto} from "../http.service";
import {Router} from "@angular/router";
import { Location } from '@angular/common';
@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {
  formdata = {
    patientId: '',
    content: '',
    date: null,
    time: null,
    type: '1'
  }
  constructor(private httpService: HttpService, private location: Location) { }

  ngOnInit(): void {
  }

  handleBack() {
    this.location.back();
  }

  handleSubmit(e: any) {
    e.preventDefault();
    this.httpService.createReminder({
      patient_id: this.formdata.patientId,
      content: this.formdata.content,
      date: String(this.formdata.date),
      time: String(this.formdata.time),
      type: String(this.formdata.type),
      doctor_id: String(localStorage.getItem('doctorId'))
    }).subscribe(res => {
      if (typeof res === 'number') {
        alert('Create successfully!');
      }
    })
  }

}
