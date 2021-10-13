import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const baseUrl = 'http://localhost:8080/api/getExchangeData';
const baseUrl2 = 'http://localhost:8080/api/listCurrencies';
const baseUrl3 = 'http://localhost:8080/api/sendCurrencies';

@Injectable({
  providedIn: 'root'
})

export class ExchangeService {

  constructor(private http: HttpClient) { }

  getData(data: object) {
    return this.http.post<any[][]>(baseUrl, data);
  }

  getCurrencies(data: object) {
    return this.http.post<object[]>(baseUrl2, data);
  }
}
