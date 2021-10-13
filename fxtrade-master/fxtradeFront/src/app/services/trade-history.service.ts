import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Trade } from '../models/trade';

const baseUrl = 'http://localhost:8080/api/getTradeData';
const CancelUrl = 'http://localhost:8080/api/cancelTradeById';
const totalUrl = 'http://localhost:8080/api/getTotalProfit';

@Injectable({
  providedIn: 'root'
})
export class TradeHistoryService {

  constructor(private http: HttpClient) { }

  getTradeData(data:any) {
    return this.http.post<any[]>(baseUrl, data);
  }

  DeleteTradeById(data:String) {
    return this.http.post<Trade>(CancelUrl, data);
  }

  getTotalProfit(data:any) {
    return this.http.post<number>(totalUrl, data);
  }

}
