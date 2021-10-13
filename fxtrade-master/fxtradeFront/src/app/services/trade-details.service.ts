import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Trade } from '../models/trade';

const baseUrl = 'http://localhost:8080/api/findTradeById';

@Injectable({
  providedIn: 'root'
})
export class TradeDetailsService {

  constructor(private http: HttpClient) { }

  findTradeById(data:String):Observable<Trade> {
    return this.http.post<Trade>(baseUrl, data);
  }
}
