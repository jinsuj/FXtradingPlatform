import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { Observable } from "rxjs";
import { User } from '../models/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  isLoggedIn : Observable<boolean>;
  isAdmin : Observable<boolean>;
  username: string = '';
  password: string = '';

  constructor(private router: Router, private logInService: LoginService) { 
    this.isLoggedIn = logInService.isLoggedIn();
  }

  ngOnInit(): void {
  }

  logIn(): void {
    const data = {
      username: this.username,
      password: this.password
    };
    this.logInService.logIn(data)
      .subscribe ( data => {
        this.router.navigate(['/exchange']);
        console.log(data);
        alert('Login success.');
      },
      error => {
        alert('Invalid Username/Password');
        this.logInService.logout();
        console.log(error);
      });
      console.log(this.username);
  }

  logOut(): void {
    this.logInService.logout();
  }

  forgotPass(): void {
    this.router.navigate(['/askUsername']);
  }

}
