import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { DataService } from "./services/data.service";
//data table imports
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { Ng2OrderModule } from 'ng2-order-pipe';
import {NgxPaginationModule} from 'ngx-pagination';
import * as $ from 'jquery';
import { LayoutModule } from "./common/layout/layout.module";
import { LoginModule } from "./login/login.module";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    Ng2SearchPipeModule ,
    Ng2OrderModule,
    NgxPaginationModule,
    LayoutModule,
    LoginModule
  ],
  providers: [DataService],
  bootstrap: [AppComponent],
  exports:[]
})
export class AppModule { }
