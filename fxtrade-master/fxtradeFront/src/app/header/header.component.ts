import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { LoginService } from '../services/login.service';
import { RouteGuardService } from '../services/route-guard.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  isLoggedIn : Observable<boolean>;
  isAdmin : Observable<boolean>;

  constructor(private router: Router, private logInService: LoginService, private routerGuardService: RouteGuardService) { 
    this.isLoggedIn = logInService.isLoggedIn();
    this.isAdmin = logInService.isAdmin();
  }

  ngOnInit(): void {
  }

  logOut(): void {
    this.logInService.deauthenticate();
    this.logInService.logout();
    localStorage.removeItem('username');
  }

}
