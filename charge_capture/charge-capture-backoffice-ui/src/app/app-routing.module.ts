import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes , RouterModule, Router} from '@angular/router';
import { PatientListComponent } from './patientlist/patientlist.component';
import { PatientdetailsComponent } from './patientdetails/patientdetails.component';

const routes:Routes=[
  { path: '',  redirectTo: '/patientlist', pathMatch: 'full' },
  { path: 'patientlist',component:PatientListComponent},
  { path: 'patientdetails',component:PatientdetailsComponent},
    
];
@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports:[RouterModule]
})
export class AppRoutingModule { }
