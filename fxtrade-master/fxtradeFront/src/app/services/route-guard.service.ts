import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class RouteGuardService implements CanActivate{

  constructor(
    private loginService : LoginService,
    private router : Router
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    console.log("a Guard");
    if (localStorage.getItem('user') !== null){
      return true;
    }
    else {
      this.router.navigate(['register']);
      return false;
    }
  }

}

@Injectable({
  providedIn: 'root'
})
export class AdminGuardService implements CanActivate {

  constructor(
    private loginService : LoginService,
    private router : Router
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    console.log("admin time");
    console.log(this.loginService.isAdminSubject.value);
    if (this.loginService.isAdminSubject.value == true) {
      return true;
    } else {
      this.router.navigate(['exchange']);
      return false;
    }
  }
}