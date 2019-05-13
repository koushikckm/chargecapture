import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PatientListComponent } from './patientlist.component';

const routes: Routes = [
  {
    path: '',
    component: PatientListComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PatientListRoutingModule { }
