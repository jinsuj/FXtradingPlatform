import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders } from '@angular/common/http';
import { Trade } from 'src/app/models/trade';
import { Observable } from 'rxjs';

const baseUrl = 'http://localhost:8080/api/getUserId';
const baseUrl2 = 'http://localhost:8080/api/getCurrencyId';
const baseUrl3 = 'http://localhost:8080/api/getIsBuy';
@Injectable({
  providedIn: 'root'
})
export class MakeTradeService {

private makeTradeUrl: string;

constructor(private http: HttpClient) {
 this.makeTradeUrl = 'http://localhost:8080/api/trade';
}

 // tslint:disable-next-line:typedef
 public makeNewTrade(data: Trade): Observable<Trade> {

  // localStorage.setItem('trade', JSON.stringify(data));
  return this.http.post<Trade>(this.makeTradeUrl, data);
}

getCurrencyId(data: any) {
  return this.http.post<any[]>(baseUrl2, data);
}

getIsBuy(data: string) {
  return this.http.post(baseUrl3, data);
}



}
