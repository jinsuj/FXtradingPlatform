import { Component, Injectable, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders } from '@angular/common/http';
import { TradeDetailsService } from 'src/app/services/trade-details.service';
import { CurrencyService } from 'src/app/services/currency-service.service';
import {Router, ActivatedRoute, Params} from '@angular/router';
import { Trade } from '../models/trade';
import { Currency } from '../models/currency';
import { LotSizesEnum } from '../models/Lot-sizes-enum.model';

@Component({
  selector: 'app-home',
  templateUrl: './trade-details.component.html',
  styleUrls: ['./trade-details.component.css']
})
export class TradeDetailsComponent implements OnInit {

  curFrom: Currency = new Currency();
  curTo: Currency = new Currency();
  trade: Trade = new Trade() ;
  constructor(private http: HttpClient, private tradeService: TradeDetailsService, private currencyService: CurrencyService, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      const tradeId = params['id'];
      console.log(tradeId);
      const tradeField = document.getElementById("trade-id");
      if (tradeField) tradeField.innerHTML = "Trade #" + tradeId;
      this.searchTrade(tradeId);
    });
   }

   searchTrade(id: String): void {
    this.tradeService.findTradeById(id).subscribe(data => {
      this.trade = data;
      const details = document.getElementById("details");
      if (details) details.hidden = false;
      const help = document.getElementById("help");
      if (help) help.hidden = false;
      const loading = document.getElementById("loading");
      if (loading) loading.hidden = true;

      const date = this.trade.date;
      const userId = this.trade.user_id;
      const currencyFrom = this.trade.currencyfrom_id;
      const currencyTo = this.trade.currencyto_id;
      const lotSize = this.trade.lotSize;
      var volume = 0;
      if (lotSize.toString() == "NANO"){
        volume = 100;
      } else if (lotSize.toString() == "MICRO"){
        volume = 1000;
      } else if (lotSize.toString() == "MINI"){
        volume = 10000;
      } else if (lotSize.toString() == "STANDARD") {
        volume = 100000;
      }
      const profit = this.trade.profit;

      const typeElement = document.getElementById("trade-type");
      const dateElement = document.getElementById("date");
      const userIdElement = document.getElementById("user-id");
      const currencyFromElement = document.getElementById("currency-from");
      const currencyToElement = document.getElementById("currency-to");
      const lotSizeElement = document.getElementById("lot-size");
      const volumeElement = document.getElementById("volume");
      const profitElement = document.getElementById("profit");

      this.currencyService.findCurrencySymbolById(currencyFrom.toString()).subscribe(data2 => {
        this.curFrom = data2;
        const currencyFromName = this.curFrom.symbol;
        if (currencyFromElement) currencyFromElement.innerHTML = "" + currencyFromName;
      });

      this.currencyService.findCurrencySymbolById(currencyTo.toString()).subscribe(data3 => {
        this.curTo = data3;
        const currencyToName = this.curTo.symbol;
        if (currencyToElement) currencyToElement.innerHTML = "" + currencyToName;
      });

      if (profit == 0) {
        if (typeElement) typeElement.innerHTML = "Buy"
      } else {
        if (typeElement) typeElement.innerHTML = "Sell"
      }
      if (dateElement) dateElement.innerHTML = "" + date;
      if (userIdElement) userIdElement.innerHTML = "" + userId;
      if (lotSizeElement) lotSizeElement.innerHTML = "" + lotSize;
      if (volumeElement) volumeElement.innerHTML = "" + volume;
      if (profitElement) profitElement.innerHTML = "" + profit;

    },
    error => {
      const loading = document.getElementById("loading");
      const errorMessage = document.getElementById("error");
      if (loading) loading.hidden = true;
      if (errorMessage) errorMessage.hidden = false;
    });

   }

}
