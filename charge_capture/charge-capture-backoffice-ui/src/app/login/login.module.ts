import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
//data table imports
import * as $ from 'jquery';
import { LoginComponent } from './login.component';
@NgModule({
  declarations: [
    LoginComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,ReactiveFormsModule
  ],
  providers: [],
  bootstrap: []
})
export class LoginModule { }
