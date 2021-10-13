import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ExchangeService } from 'src/app/services/exchange.service';
import { LoginService } from 'src/app/services/login.service';
import { Observable } from "rxjs";

@Component({
  selector: 'app-exchange',
  templateUrl: './exchange.component.html',
  styleUrls: ['./exchange.component.css']
})
export class ExchangeComponent implements OnInit {

  exchangeData: any[][] = [[],[]];
  datas: any;

  isLoggedIn: Observable<boolean>;

  constructor(private router: Router, private exchangeService: ExchangeService, private loginService: LoginService) {
    this.isLoggedIn = loginService.isLoggedIn();
   }

  ngOnInit(): void {
    const data = {
      currencyFrom_id: 0,
      currencyTo_id: 1
    };
    this.exchangeService.getData(data)
      .subscribe(data => {
        this.exchangeData = data;
      });
      console.log(this.exchangeData);
  }

  
  goTrade(data: any): void {
    localStorage.setItem("fromCurrency", data[4]);
    localStorage.setItem("toCurrency", data[5]);
    this.router.navigate(['/make_trade']);
  }

  logOut(): void {
    this.loginService.logout();
  }

}
