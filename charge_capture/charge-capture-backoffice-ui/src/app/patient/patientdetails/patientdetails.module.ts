import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PatientdetailsComponent } from './patientdetails.component';
import { PatientListRoutingModule } from './patientdetails-routing.module';
import { HttpClientModule } from '@angular/common/http';
//data table imports
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { Ng2OrderModule } from 'ng2-order-pipe';
import {NgxPaginationModule} from 'ngx-pagination';
import {A2Edatetimepicker} from 'ng2-eonasdan-datetimepicker';
import * as $ from 'jquery';
import {OnlyNumber} from '../../Directives/numberDirective.directive';
import { DatePipe } from '@angular/common';
import { OrderrByPipe } from "./order.pipe";
@NgModule({
  declarations: [
    OnlyNumber,
    PatientdetailsComponent,
    OrderrByPipe
  ],
  imports: [
    FormsModule,
    CommonModule,
    HttpClientModule,
    Ng2SearchPipeModule,
    Ng2OrderModule,
    NgxPaginationModule,
    PatientListRoutingModule,
    A2Edatetimepicker
  ],
  providers:[DatePipe]
})
export class PatientDetailsModule { }
