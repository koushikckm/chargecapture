import { Component, OnInit } from '@angular/core';
import { NgModule } from '@angular/core';
import { FormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { FormGroup, FormControl } from '@angular/forms';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {

  constructor(private route:Router) { }

  ngOnInit() {
  }
  loginForm = new FormGroup({
    username  : new FormControl('',[Validators.required]),
    password: new FormControl('',[Validators.required]),
  });

  login(){
    let loginObject=this.loginForm.value;
    if(loginObject.username=="root" && loginObject.password=="root"){
      this.route.navigateByUrl('/dashboard');
      return;
    }
    else{
      this.loginForm['error']="Incorrect username or password";
      this.loginForm.get('username').setValue('');
      this.loginForm.get('password').setValue('');
      return;
    }
  }

}
