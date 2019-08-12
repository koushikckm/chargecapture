import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { UsersComponent } from './users.component';
import { HttpClientModule } from '@angular/common/http';
//data table imports
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { Ng2OrderModule } from 'ng2-order-pipe';
import {NgxPaginationModule} from 'ngx-pagination';
import { FilterPipe } from './filter.pipe';
import { OrderrByPipe } from "./orderby.pipe";
import {A2Edatetimepicker} from 'ng2-eonasdan-datetimepicker';
import { UsersRoutingModule } from "./users-routing.module";
@NgModule({
  declarations: [
    FilterPipe,OrderrByPipe,UsersComponent
  ],
  imports: [
    FormsModule,
    CommonModule,
    UsersRoutingModule,
    HttpClientModule,
    Ng2SearchPipeModule,
    Ng2OrderModule,
    NgxPaginationModule,   
    A2Edatetimepicker 
  ],
})
export class UsersModule { }
