import { Component, HostBinding,OnInit } from '@angular/core';
import { Router  , ActivatedRoute } from '@angular/router';
import { Location, LocationStrategy, PathLocationStrategy } from '@angular/common';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent implements OnInit {
  title = 'charge-capture-backoffice-ui';
  constructor(private route: ActivatedRoute,private location: Location){}
  active:false;
  ngOnInit() {
    console.log(this.location.path());
  }
  backGroundStyle:any='charge-capture-background-theme-orange';  
}
