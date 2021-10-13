import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SecurityQuestionService } from 'src/app/services/security-question.service';
import { Observable } from "rxjs";
import { AskusernameComponent } from 'src/app/askusername/askusername.component';

@Component({
  selector: 'app-security-question',
  templateUrl: './security-question.component.html',
  styleUrls: ['./security-question.component.css']
})
export class SecurityQuestionComponent implements OnInit {
  ans1 = '';
  questions: any = [];
  constructor(private router: Router, private securityService: SecurityQuestionService) { }

  ngOnInit(): void {
    const data = {
      username: localStorage.getItem('username')
    };
    this.securityService.getQuestions(data)
    .subscribe(data => {
      this.questions = data;
      console.log(this.questions);
    })
  }

  submitAns(): void {
    const data = {
      username: localStorage.getItem('username'),
      ans1: this.ans1
    };
    this.securityService.submitAns(data)
      .subscribe(data => {
        this.router.navigate(['/update']);
      },
      error => {
        alert('Answer did not match the security question');
      })
  }
}
