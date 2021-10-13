import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';

const baseUrl = 'http://localhost:8080/api/login';
const idUrl = 'http://localhost:8080/api/getUserIdForTrade';
const adminUrl = 'http://localhost:8080/api/getIsAdmin';


@Injectable({
  providedIn: 'root'
})
export class LoginService {

  isLoginSubject = new BehaviorSubject<boolean>(this.hasToken());
  isAdminSubject = new BehaviorSubject<boolean>(this.hasToken());

  constructor(private http: HttpClient) { }

  isLoggedIn() : Observable<boolean> {
    return this.isLoginSubject.asObservable();
  }

  isAdmin() : Observable<boolean> {
    return this.isAdminSubject.asObservable();
  }

  logIn(data:Object): Observable<Object> {
    localStorage.setItem('user', JSON.stringify(data));
    localStorage.setItem('username', JSON.stringify( Object.values(data)[0]));
    this.isLoginSubject.next(true);
    this.getIsAdmin(JSON.stringify(Object.values(data)[0]))
      .subscribe(data => {
        console.log(data);
        if (data == true) {
          this.authenticate();
        } else {
          this.deauthenticate();
        }
      });
    return this.http.post(baseUrl, data);
  }

  getUserId(data: string): Observable<object> {

    return this.http.post<any>(idUrl, data);
  }

  getIsAdmin(data: string): Observable<boolean> {
    return this.http.post<any>(adminUrl, data);
  }

  logout() : void {
    localStorage.removeItem('user');
    this.isLoginSubject.next(false);
  }

  private hasToken() : boolean {
    return !!localStorage.getItem('user');
  }

  public authenticate() {
    this.isAdminSubject.next(true);
  }

  public deauthenticate() {
    this.isAdminSubject.next(false);
  }
}
