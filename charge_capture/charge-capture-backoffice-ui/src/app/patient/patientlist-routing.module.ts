import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PatientListComponent } from './patientlist.component';

const routes: Routes = [
  {
    path: '',
    component: PatientListComponent
  },
  
    { path: 'patientdetails/:patientId',
    loadChildren:'./patientdetails/patientdetails.module#PatientDetailsModule'},
  
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PatientListRoutingModule { }
