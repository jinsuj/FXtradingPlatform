import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TradeHistoryComponent } from './trade-history.component';
import { NgxPaginationModule } from 'ngx-pagination';



@NgModule({
  declarations: [TradeHistoryComponent],
  imports: [CommonModule,NgxPaginationModule], 
  exports: [TradeHistoryComponent]
})
export class TradeHistoryModule { }
