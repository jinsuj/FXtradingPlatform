import { Component } from '@angular/core';
import { LoginComponent } from './login/login.component';
import { BehaviorSubject, Observable } from 'rxjs';
import { LoginService } from 'src/app/services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  isLoggedIn : Observable<boolean>;
  title = 'fxtradeFront';

  constructor(private router: Router, private logInService: LoginService) { 
    this.isLoggedIn = logInService.isLoggedIn();
  }

  logOut(): void {
    this.logInService.logout();
  }
}
