import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

export interface ReminderDto {
  content: string;
  date: string;
  time: string;
  type: string;
  patient_id: string;
  doctor_id: string;
}

@Injectable({
  providedIn: 'root'
})
export class HttpService {
  public doctor: any = null;
  constructor(private httpClient: HttpClient) {
    this.getDoctorData(localStorage.getItem('doctorId') as string);
  }

  getRemind(remindId: string) {
    return this.httpClient.get(`http://20.248.197.54:8080/reminder/${remindId}`);
  }

  getDoctorData(doctorId: string) {
    this.httpClient.get(`http://20.248.197.54:8080/doctor/${doctorId}`)
      .subscribe(data => {
        this.doctor = data;
      })
  }

  login(doctorId: string, password: string) {
    return this.httpClient.post(`http://20.248.197.54:8080/isDoctor?id=${doctorId}&password=${password}`, {});
  }

  createReminder(reminder: ReminderDto) {
    const params = new HttpParams({fromObject: {...reminder}});
    return this.httpClient.post(`http://20.248.197.54:8080/reminder`, {}, {params});
  }
}
