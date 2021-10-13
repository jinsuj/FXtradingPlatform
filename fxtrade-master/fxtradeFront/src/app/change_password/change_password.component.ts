import { Component, OnInit } from '@angular/core';
import { UpdatePasswordService } from 'src/app/services/update-password.service';
import { AskusernameComponent } from 'src/app/askusername/askusername.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './change_password.component.html',
  styleUrls: ['./change_password.component.css']
})

export class ChangePasswordComponent implements OnInit {

  newPassword= '';
  confirmPass = '';

  constructor(private router: Router, private updateService: UpdatePasswordService) { }

  ngOnInit(): void {
  }

  resetPassword(): void {
    if (this.validate() == true){
      const data = {
        username: localStorage.getItem('username'),
        password: this.newPassword,
        confirmPass: this.confirmPass
      };
      this.updateService.resetPassword(data)
      .subscribe ( data => {
        console.log(data);
        alert('Password has been updated');
        this.router.navigate(['/login']);
      },
      error => {
        console.log(error);
        alert('New password should be different than the previous password');
      });
    }
  }

  validate() {
    var password = (document.getElementById("password") as HTMLInputElement).value;
    var confirmPassword = (document.getElementById("confirmPassword") as HTMLInputElement).value;

    if (password == "" || password == null){
      alert("Please provide a password.");
      return false;
    }

    if (confirmPassword == "" || confirmPassword == null){
      alert("Please confirm your password.")
      return false;
    }

    var passwordRegex = new RegExp("^(?=.{8,})(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=.]).*$");
    if (passwordRegex.test(password) == false){
      alert("Password does not meet the requirements.");
      return false;
    }

    if (password != confirmPassword){
      alert("Passwords do not match.");
      return false;
    }

    return true;
  }
}

