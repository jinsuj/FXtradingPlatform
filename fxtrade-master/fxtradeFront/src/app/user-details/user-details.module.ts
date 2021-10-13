import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TradeHistoryModule } from '../trade-history/trade-history.module';
import { UserDetailsComponent } from './user-details.component';



@NgModule({
  declarations: [UserDetailsComponent],
  imports: [
    CommonModule,TradeHistoryModule
  ]
})
export class UserDetailsModule { }
