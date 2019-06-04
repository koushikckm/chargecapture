import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { PatientListComponent } from './patientlist.component';
import { PatientListRoutingModule } from './patientlist-routing.module';
import { HttpClientModule } from '@angular/common/http';
//data table imports
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { Ng2OrderModule } from 'ng2-order-pipe';
import {NgxPaginationModule} from 'ngx-pagination';
import { FilterPipe } from './filter.pipe';
import { OrderrByPipe } from "./orderby.pipe";
@NgModule({
  declarations: [
    PatientListComponent,FilterPipe,OrderrByPipe
  ],
  imports: [
    FormsModule,
    CommonModule,
    PatientListRoutingModule,
    HttpClientModule,
    Ng2SearchPipeModule,
    Ng2OrderModule,
    NgxPaginationModule,    
  ],
})
export class PatientlistModule { }
