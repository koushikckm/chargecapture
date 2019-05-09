import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  imagePath:any = "FocusScript_logo.jpg";
  constructor() { }

  ngOnInit() {
    document.documentElement.style.setProperty('--customPrimaryColor', '#faa730');
  	document.documentElement.style.setProperty('--customSecondaryColor', '#acacac');
  	document.documentElement.style.setProperty('--customThirdColor', '#666666');
  }

}
