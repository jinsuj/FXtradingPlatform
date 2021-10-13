import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ChangePasswordComponent } from './change_password/change_password.component';
import { AskusernameComponent } from './askusername/askusername.component';
import { HomeComponent } from './home/home.component';
import { SecurityQuestionComponent } from './security-question/security-question.component';
import { RouteGuardService, AdminGuardService } from './services/route-guard.service';
import { MakeTradeComponent} from './make-trade/make-trade.component';
import { ExchangeComponent } from './exchange/exchange.component';
import { TradeDetailsComponent } from './trade-details/trade-details.component';
import { TradeHistoryComponent } from './trade-history/trade-history.component';
import { ListuserComponent } from './listuser/listuser.component';
import { UserDetailsComponent } from './user-details/user-details.component';

const routes: Routes = [
  { path: '', component: HomeComponent},
  { path: 'login', component: LoginComponent},
  { path: 'register', component: RegisterComponent},
  { path: 'update', component: ChangePasswordComponent},
  { path: 'askUsername', component: AskusernameComponent},
  { path: 'home', component: HomeComponent},
  { path: 'security', component: SecurityQuestionComponent},
  { path: 'make_trade', component : MakeTradeComponent, canActivate: [RouteGuardService]},
  { path: 'exchange', component: ExchangeComponent, canActivate: [RouteGuardService]},
  { path: 'trade-details', component: TradeDetailsComponent, canActivate: [RouteGuardService]},
  { path: 'trade-history', component: TradeHistoryComponent, canActivate: [RouteGuardService]},
  { path: 'userList', component: ListuserComponent, canActivate: [AdminGuardService]},
  { path: 'user-details', component: UserDetailsComponent, canActivate: [RouteGuardService]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
