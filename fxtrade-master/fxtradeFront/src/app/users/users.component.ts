import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders } from '@angular/common/http';
import { UserDetailsService } from 'src/app/services/user-details.service';
import {Router, ActivatedRoute, Params} from '@angular/router';
// import { JwPaginationComponent } from 'jw-angular-pagination';

@Component({
  selector: 'app-home',
  templateUrl: './users.component.html'
})
export class UsersComponent implements OnInit {

  constructor(private http: HttpClient, private userService: UserDetailsService, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      const userId = params['id'];
      console.log(userId);
      const userField = document.getElementById("user-id");
      if (userField) userField.innerHTML = "User: " + userId;
    });
   }

}
