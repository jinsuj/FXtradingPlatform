import { Injectable } from '@angular/core';
import { User } from 'src/app/models/user';
import {HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
	providedIn: 'root'
  })
export class RegisterService {

private registrationUrl : string;

constructor(private http: HttpClient) {
	this.registrationUrl = 'http://localhost:8080/register';
} 

public registerNewUser (user:User):Observable<User> {
	return this.http.post<User>(this.registrationUrl, user);
}



}
