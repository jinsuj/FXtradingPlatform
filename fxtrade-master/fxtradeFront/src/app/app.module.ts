import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { NavbarComponent } from './navbar/navbar.component';
import { RegisterComponent } from './register/register.component';
import { ChangePasswordComponent } from './change_password/change_password.component';
import { AskusernameComponent } from './askusername/askusername.component';
import { HomeComponent } from './home/home.component';
import { SecurityQuestionComponent } from './security-question/security-question.component';
import { MakeTradeComponent } from './make-trade/make-trade.component';
import { ExchangeComponent } from './exchange/exchange.component';
import { HeaderComponent } from './header/header.component';
import { TradeDetailsComponent } from './trade-details/trade-details.component';
import { TradeHistoryComponent } from './trade-history/trade-history.component';
import {NgxPaginationModule} from 'ngx-pagination';
import { UserDetailsComponent } from './user-details/user-details.component';
import { ListuserComponent } from './listuser/listuser.component';
import { TradeHistoryModule } from './trade-history/trade-history.module';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NavbarComponent,
    RegisterComponent,
    ChangePasswordComponent,
    AskusernameComponent,
    HomeComponent,
    SecurityQuestionComponent,
    MakeTradeComponent,
    ExchangeComponent,
    HeaderComponent,
    TradeDetailsComponent,
    UserDetailsComponent,
    ListuserComponent,
    // TradeHistoryComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgxPaginationModule,
    TradeHistoryModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
