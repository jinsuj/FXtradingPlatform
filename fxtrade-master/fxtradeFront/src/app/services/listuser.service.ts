import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

const baseUrl = 'http://localhost:8080/api/getUserList';

@Injectable({
  providedIn: 'root'
})
export class ListuserService {

  constructor(private http: HttpClient) { }

  getData() {
    return this.http.get<any[][]>(baseUrl);
  }
}
