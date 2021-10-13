import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';

const baseUrl = 'http://localhost:8080/api/getQuestions';
const baseUrl2 = 'http://localhost:8080/api/submitAns';

@Injectable({
  providedIn: 'root'
})
export class SecurityQuestionService {

  constructor(private http: HttpClient) { }

  getQuestions(data:object) {
      return this.http.post<String>(baseUrl, data);
  }

  submitAns(data:object) {
    return this.http.post(baseUrl2, data);
  }

}
