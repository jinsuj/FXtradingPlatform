import { Component, OnInit } from '@angular/core';
import { LotSizesEnum } from '../models/Lot-sizes-enum.model';
import { MakeTradeService } from '../services/make-trade.service';
import { Router } from '@angular/router';
import { Trade } from 'src/app/models/trade';
import { NgForm } from '@angular/forms';
import { getLocaleDateTimeFormat } from '@angular/common';
import { User } from 'src/app/models/user';
import { ExchangeService } from '../services/exchange.service';
import {LotSize} from 'src/app/models/lot-size';
import { LoginService } from '../services/login.service';
import { isNull } from '@angular/compiler/src/output/output_ast';
// import {ModalModule} from 'ngd-modal';


@Component({
  selector: 'app-make-trade',
  templateUrl: './make-trade.component.html',
  styleUrls: ['./make-trade.component.css']
})
export class MakeTradeComponent implements OnInit {
  trade: Trade  = new Trade();
  currencyfrom = JSON.stringify(localStorage.getItem("fromCurrency"));
  currencyto = JSON.stringify(localStorage.getItem("toCurrency"));
  profit = Number();
  isBuy = false;
  lotSizes: string[];
  lotSizesEnum = LotSizesEnum;
  returnUrl = './exchange';
  curUrl = './make_trade';
  currencyList: object[];
  lotS = new LotSize();



  constructor(private makeTradeService: MakeTradeService, private router: Router, private exchangeService: ExchangeService,
              private loginService: LoginService) {
this.lotSizes = Object.keys(this.lotSizesEnum).filter(Number) ;
}



ngOnInit(): void  {
  // tslint:disable-next-line:no-non-null-assertion
  const email = JSON.parse(localStorage.getItem('username')!);
  const data2 = {};
  this.exchangeService.getCurrencies(data2)
  .subscribe(data => {
    this.currencyList = data;
  });
  console.log(this.currencyList);


  this.loginService.getUserId(email)
  .subscribe((data: any) => {
    localStorage.setItem('userId', JSON.stringify(data));
  });


}

  cancelTrade(): void {

    this.router.navigate([this.returnUrl]);
  }

  makeNewTrade(form: NgForm): void {
    const data = {

      curToSymbol: this.currencyto,
      curFromSymbol: this.currencyfrom,
    };
    console.log('------');
    console.log(form.value);
    console.log(this.lotS.value);
    console.log( form.value.lotSize + ' ::: ' + form.value.isBuy );
    if ( form.value.lotSize == null ||   form.value.isBuy == null ){
      alert('Invalid inputs');
      return;
    }
    console.log(localStorage.getItem('userId'));
    if ( localStorage.getItem('userId') != null ) {
    this.trade.currencyfrom_id = +this.findCurId(this.currencyfrom);
    this.trade.currencyto_id = +this.findCurId(this.currencyto);
    this.trade.trade_date = Date.now();
    // tslint:disable-next-line:no-non-null-assertion
    const idTemp = JSON.parse(localStorage.getItem('userId')!);
    this.trade.user_id =  +idTemp;
    this.trade.lotSize = this.lotS.value;
    console.log(this.trade.lotSize);
    console.log(this.trade);
    this.save(this.trade.trade_date);
    form.reset();
    }
  }

  private findCurId(symbol: string): string {
    symbol = JSON.parse(symbol);
    let temp = String();
    {this.currencyList.forEach(currency => {
      console.log(Object.values(currency)[0]);
      if ( Object.values(currency)[0] === symbol ){
        temp = Object.values(currency)[1];
      }
    });
     return temp;
  }
  }

  private save(date: Date): void {
    if (window.confirm('Is this trade correct?')){
    // tslint:disable-next-line:no-shadowed-variable
    this.makeTradeService.makeNewTrade(this.trade).subscribe(data => {
      // console.log(data);
      alert('Trade Submitted');
      // this.router.navigate(['/make_trade']);
      this.trade = new Trade();

    });
    this.makeTradeService.getIsBuy(JSON.stringify(this.isBuy)).subscribe(data => {
      console.log(data);
    });
  } else { console.log('Trade Cancelled'); }
  }


}
