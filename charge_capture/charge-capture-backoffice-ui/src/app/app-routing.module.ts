import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes , RouterModule, Router} from '@angular/router';
import { PatientListComponent } from './patientlist/patientlist.component';

const routes:Routes=[
  { path: '',  redirectTo: '/home', pathMatch: 'full' },
  { path: 'home',component:PatientListComponent},
  { path: 'patientdetails',loadChildren:'./patientdetails/patientdetails.module#PatientDetailsModule'}
    
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
