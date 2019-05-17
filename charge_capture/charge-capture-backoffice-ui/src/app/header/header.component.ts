import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  imagePath:any = "FocusScript_logo.jpg";
  constructor() { }
  
  ngOnInit() {}

  toggleSideMenu(){
    $('#my-wrapper').toggleClass('sidebar-open toggled');
    $('#my-sidebar-wrapper').toggleClass('toggled');
    //$('#app-content-wrapper').toggleClass('my-content-wrapper');
  }

}
