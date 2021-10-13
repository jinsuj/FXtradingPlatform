import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SecurityQuestionEnum } from '../models/security-question-enum.model';
import { User } from '../models/user';
import { RegisterService } from '../services/register-service.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  user: User = new User() ;
  questions : String[];
  questionEnum = SecurityQuestionEnum;

  constructor(private registerService: RegisterService, private router: Router) {
    this.questions = Object.keys(this.questionEnum);

  }

  ngOnInit(): void  {
  }

  save() {
    this.registerService
    .registerNewUser(this.user).subscribe(data => {
      console.log(data)
      this.user = new User();
      
    }, 
    error => {
      console.log(error)
      if (error.status == 500){
        alert("Email already exists")
        this.router.navigate(['/register']);
        return;
      } else {
        alert("Sign up completed");   
        this.gotoHome(); 
      }
      
    });
  }

  onSubmit() {
    if (this.validate() == true){
      this.save();   
      this.gotoHome(); 
    }
  }

  gotoHome() {
    this.router.navigate(['/login']);
  }

  validate() {
    var firstName = (document.getElementById("firstName") as HTMLInputElement).value;
    var lastName = (document.getElementById("lastName") as HTMLInputElement).value;
    var email = (document.getElementById("email") as HTMLInputElement).value;
    var password = (document.getElementById("password") as HTMLInputElement).value;
    var confirmPassword = (document.getElementById("confirmPassword") as HTMLInputElement).value;
    var question1 = (document.getElementById("securityQuestion1") as HTMLSelectElement);
    var q1sel = question1.selectedIndex;
    var question2 = (document.getElementById("securityQuestion2") as HTMLSelectElement);
    var q2sel = question2.selectedIndex;
    var question3 = (document.getElementById("securityQuestion3") as HTMLSelectElement);
    var q3sel = question3.selectedIndex;
    var answer1 = (document.getElementById("securityAnswer1") as HTMLInputElement).value;
    var answer2 = (document.getElementById("securityAnswer2") as HTMLInputElement).value;
    var answer3 = (document.getElementById("securityAnswer3") as HTMLInputElement).value;

    if (firstName == "" || firstName == null){
      alert("Please provide a first name.");
      return false;
    }

    if (lastName == "" || lastName == null){
      alert("Please provide a last name.");
      return false;
    }

    if (email == "" || email == null){
      alert("Please provide an email.");
      return false;
    }

    if (password == "" || password == null){
      alert("Please provide a password.");
      return false;
    }

    if (confirmPassword == "" || confirmPassword == null){
      alert("Please confirm your password.")
      return false;
    }

    if (q1sel == 0 || q2sel == 0 || q3sel == 0){
      alert("Please select the security questions to answer.");
      return false;
    }

    if (answer1 == "" || answer2 == "" || answer3 == "" || answer1 == null || answer2 == null || answer3 == null){
      alert("Please answer the security questions.");
      return false;
    }

    var nameRegex = new RegExp("[A-Z][A-Za-z,',\p{Pd}, ]{0,35}[A-Za-z,.]");
    var emailRegex = new RegExp("^[a-zA-Z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$");
    var passwordRegex = new RegExp("^(?=.{8,})(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=.]).*$");

    if (nameRegex.test(firstName) == false){
      alert("Please enter a valid first name.");
      return false;
    }

    if (nameRegex.test(lastName) == false){
      alert("Please enter a valid last name.");
      return false;
    }

    if (emailRegex.test(email) == false){
      alert("Please enter a valid email address.");
      return false;
    }

    if (passwordRegex.test(password) == false){
      alert("Password does not meet the requirements.");
      return false;
    }

    if (password != confirmPassword){
      alert("Passwords do not match.");
      return false;
    }

    if (q1sel == q2sel || q1sel == q3sel || q2sel == q3sel){
      alert("Please choose different security questions");
      return false;
    }

    return true;
  }

}
