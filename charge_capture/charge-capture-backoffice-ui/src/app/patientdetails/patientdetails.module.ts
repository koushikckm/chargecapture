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
@NgModule({
  declarations: [
    PatientdetailsComponent
  ],
  imports: [
    FormsModule,
    CommonModule,
    HttpClientModule,
    Ng2SearchPipeModule,
    Ng2OrderModule,
    NgxPaginationModule,
    PatientListRoutingModule
  ],
})
export class PatientDetailsModule { }
