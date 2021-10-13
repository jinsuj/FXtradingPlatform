import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { User } from '../models/user';
import { UserDetailsService } from '../services/user-details.service';
import {NgxPaginationModule} from 'ngx-pagination';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {
  user: User = new User() ;

  constructor( private userService: UserDetailsService,private http: HttpClient, private activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      const userId = params['id'];
      console.log(userId);
      localStorage.setItem('userId', userId);
      this.userService.getUser(userId).subscribe(data => {
        this.user = data;
      });
    });
  }

}
