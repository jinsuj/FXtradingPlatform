import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';

const baseUrl = 'http://localhost:8080/api/resetPassword';

@Injectable({
  providedIn: 'root'
})
export class UpdatePasswordService {

  constructor(private http: HttpClient) { }

  resetPassword(data:object):Observable<object> {
    return this.http.post(baseUrl, data);
  }
}
