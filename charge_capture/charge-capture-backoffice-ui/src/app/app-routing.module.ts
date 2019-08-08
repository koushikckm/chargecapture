import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes , RouterModule, Router} from '@angular/router';

const routes:Routes=[
  { path: '',  redirectTo: '/home', pathMatch: 'full' },
  { path: 'home',loadChildren:'./patient/patientlist.module#PatientlistModule'},  
  { path: 'users',loadChildren:'./users/users.module#UsersModule'}
    
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
