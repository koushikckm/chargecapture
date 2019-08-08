import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
//data table imports
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { Ng2OrderModule } from 'ng2-order-pipe';
import {NgxPaginationModule} from 'ngx-pagination';
import {A2Edatetimepicker} from 'ng2-eonasdan-datetimepicker';
import * as $ from 'jquery';
import { Routes , RouterModule, Router} from '@angular/router';
import { LayoutComponent } from './layout.component';
import { HeaderComponent } from '../../header/header.component';
import { FooterComponent } from '../../footer/footer.component';
const routes:Routes=[
    
    { path: '',  redirectTo: '/home', pathMatch: 'full' },
    { path: '',component:LayoutComponent,
      children:[
        { path: 'home',loadChildren:'./patient/patientlist.module#PatientlistModule'},  
        { path: 'users',loadChildren:'./users/users.module#UsersModule'} 
      ]},

  ];
@NgModule({
  declarations: [
    LayoutComponent,HeaderComponent,FooterComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    Ng2SearchPipeModule ,
    Ng2OrderModule,
    NgxPaginationModule,A2Edatetimepicker,
    RouterModule.forChild(routes)
  ],
  providers: [],
  bootstrap: []
})
export class LayoutModule { }
