import { Component, OnInit } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { LoginService } from 'src/app/services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  isLoggedIn : Observable<boolean>;

  constructor(private router: Router, private logInService: LoginService) { 
    this.isLoggedIn = logInService.isLoggedIn();
  }

  ngOnInit(): void {
  }

  logOut(): void {
    this.logInService.logout();
  }

}
