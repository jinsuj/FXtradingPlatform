import { Component, OnInit } from '@angular/core';
import { TradeHistoryService } from '../services/trade-history.service';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-trade-history',
  templateUrl: './trade-history.component.html',
  styleUrls: ['./trade-history.component.css']
})
export class TradeHistoryComponent implements OnInit {
  tradeData: any[][] = [[],[]];
  page = 1;
  count = 0;
  tableSize = 7;
  tableSizes = [7 , 14 , 21, 35];
  total = 0;

  constructor( private tradeHistoryService: TradeHistoryService, private router: Router, private loginService: LoginService) { }

  ngOnInit(): void {
    this.getHistory();
    const email = JSON.parse(localStorage.getItem('username')!);
    this.loginService.getUserId(email)
      .subscribe((data: any) => {
        localStorage.setItem('userId', JSON.stringify(data));
    });
    const idTemp = JSON.parse(localStorage.getItem('userId')!);
    this.tradeHistoryService.getTotalProfit(idTemp)
      .subscribe(data => {
        this.total = data;
        console.log(data);
      });
  }

  private getHistory(): void {
    const email = JSON.parse(localStorage.getItem('username')!);
    this.loginService.getUserId(email)
      .subscribe((data: any) => {
        localStorage.setItem('userId', JSON.stringify(data));
    });
    const idTemp = JSON.parse(localStorage.getItem('userId')!);
    this.tradeHistoryService.getTradeData(idTemp)
      .subscribe(data => {
        this.tradeData = data;
        console.log(this.tradeData);
    });
  }

  onTableDataChange(event: any){
    this.page = event;
    this.getHistory();
  }

  onTableSizeChange(event: any): void {
    this.tableSize = event.target.value;
    this.page = 1;
    this.getHistory();
  }

  goDetails(id: string): void {
    this.router.navigate(['/trade-details'], {queryParams: { id: id }}) ;
  }

  cancelTrade(id: string): void {
    if (window.confirm('Cancel trade of ID: ' + id + ', correct ?')) {
      this.tradeHistoryService.DeleteTradeById(id).subscribe(data => {
       console.log('Trade Canceled');
       this.ngOnInit();
      });

    }
  }

}
