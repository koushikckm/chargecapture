import { Component, HostBinding } from '@angular/core';
import { Router  , ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'charge-capture-backoffice-ui';
  constructor(private route: ActivatedRoute){}
  active:false;
  ngOnInit() {
    console.log(this.route);
  }
}
