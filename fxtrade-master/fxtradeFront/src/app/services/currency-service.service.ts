import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Currency } from '../models/currency';

const baseUrl = 'http://localhost:8080/api/findCurrencySymbolById';

@Injectable({
  providedIn: 'root'
})
export class CurrencyService {

  constructor(private http: HttpClient) { }

  findCurrencySymbolById(data:String):Observable<Currency> {
    return this.http.post<Currency>(baseUrl, data);
  }
}
