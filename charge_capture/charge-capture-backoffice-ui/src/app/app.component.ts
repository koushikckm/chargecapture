import { Component, HostBinding } from '@angular/core';
import { Router  , ActivatedRoute } from '@angular/router';
import { Location, LocationStrategy, PathLocationStrategy } from '@angular/common';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [Location, {provide: LocationStrategy, useClass: PathLocationStrategy}]
})
export class AppComponent {
  title = 'charge-capture-backoffice-ui';
  constructor(private route: ActivatedRoute,private location: Location){}
  active:false;
  ngOnInit() {
    console.log(this.location.path());
  }
  backGroundStyle:any='charge-capture-background-theme-orange';  
}
