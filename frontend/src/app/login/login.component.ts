import { Component, OnInit } from '@angular/core';
import {HttpService} from "../http.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  formdata = {
    doctorId: '',
    password: ''
  }
  constructor(private httpService: HttpService, private router: Router) { }

  ngOnInit(): void {
  }

  handleSubmit() {
    this.httpService.login(this.formdata.doctorId, this.formdata.password)
      .subscribe((data: any) => {
        if (data) {
          this.router.navigate(['dashboard']);
          localStorage.setItem('doctorId', this.formdata.doctorId);
        } else {
          alert('Invalid credentials!')
        }

      })
  }
}
