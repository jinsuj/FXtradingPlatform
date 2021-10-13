import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { AskusernameService } from 'src/app/services/askusername.service';

@Component({
  selector: 'app-askusername',
  templateUrl: './askusername.component.html',
  styleUrls: ['./askusername.component.css']
})
export class AskusernameComponent implements OnInit {
  username = '';

  constructor(private router: Router, private askUsernameService: AskusernameService) { }

  ngOnInit(): void {
  }

  askUsername(): void {
    const data = {
      username: this.username
    };
    this.askUsernameService.askUsername(data)
    .subscribe (data => {
      console.log(data)
      this.router.navigate(['/security']);
    },
    error => {
      console.log(error);
      alert('User does not exist');
    });
  }

}
