import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes , RouterModule, Router} from '@angular/router';
import { LoginComponent } from './login/login.component';

const routes:Routes=[
  { path: '',  redirectTo: '/login', pathMatch: 'full' },
  {path:'login',component:LoginComponent},  
 { path: 'dashboard',loadChildren:'./common/layout/layout.module#LayoutModule'}
    
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
