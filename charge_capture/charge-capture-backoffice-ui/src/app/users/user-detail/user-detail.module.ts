import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { UserDetailRoutingModule } from "./user-detail-routing.module";
//data table imports
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { Ng2OrderModule } from 'ng2-order-pipe';
import {NgxPaginationModule} from 'ngx-pagination';
import {A2Edatetimepicker} from 'ng2-eonasdan-datetimepicker';
import { UserDetailComponent } from './user-detail.component';
@NgModule({
  declarations: [UserDetailComponent],
  imports: [
    UserDetailRoutingModule,
    FormsModule,
    CommonModule,
    HttpClientModule,
    Ng2SearchPipeModule,
    Ng2OrderModule,
    NgxPaginationModule,   
    A2Edatetimepicker 
  ],
})
export class UsersModule { }
