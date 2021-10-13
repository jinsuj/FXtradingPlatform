import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';

const baseUrl = 'http://localhost:8080/api/askUsername';

@Injectable({
  providedIn: 'root'
})
export class AskusernameService {

  constructor(private http: HttpClient) { }

  askUsername(data:object):Observable<object> {
    localStorage.setItem('username', JSON.stringify(data));
    return this.http.post(baseUrl, data);
  }
}
