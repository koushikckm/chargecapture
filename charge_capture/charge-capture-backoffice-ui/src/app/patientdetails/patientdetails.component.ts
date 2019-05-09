import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DataService } from "../services/data.service";
@Component({
  selector: 'app-patientdetails',
  templateUrl: './patientdetails.component.html',
  styleUrls: ['./patientdetails.component.css']
})
export class PatientdetailsComponent implements OnInit {

  constructor(
    private router:Router,
    private data:DataService
  ) { }
  patientdetails:any=null;
  patientDetailsShow:boolean=true;
  showPatientServiceDetails:boolean=false;
  showPatientServiceDetailsPanel:boolean=true;
  isEditable:boolean=false;
    //sorting
    key: string = 'patientId'; //set default
    reverse: boolean = false;
    sort(key){
      this.key = key;
      this.reverse = !this.reverse;
    }
  
    //initializing p to one
    pagenumber: number = 1;

  ngOnInit() {
    if(this.data.getData()!=null){
      this.patientdetails=this.data.getData();
      console.log(this.patientdetails);
    }
    else{
      this.router.navigate(['/patientlist']); //else call api
    }
  }
  hidePanel(prop){
    if(this[prop]==true){
      this[prop]=false;
    }
    else{
      this[prop]=true;
    }
  }

  serviceDetails:any=null;
  showServiceDetails(details){
    this.serviceDetails=details;
    this.showPatientServiceDetails=true;
    this.showPatientServiceDetailsPanel=true;
  }
  edit(){
    console.log("edit clicked");
    this.isEditable=true;
  }
  save(patientdetails){
    console.log(patientdetails);
  }
}
