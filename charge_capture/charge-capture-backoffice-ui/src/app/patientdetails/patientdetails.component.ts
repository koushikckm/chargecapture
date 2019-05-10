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
  patientdetails:any={};
  patientDetailsShow:boolean=true;
  showPatientServiceDetails:boolean=false;
  showPatientServiceDetailsPanel:boolean=true;
  patientServiceDetailsShow:boolean=true;
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
    oldPatientDetails={};
  ngOnInit() {
    console.log("Details component1");
    
    if(this.data.getData()!=null){
      this.patientdetails=this.data.getData();
      
      this.oldPatientDetails=JSON.parse(JSON.stringify( this.patientdetails ));
      console.log(this.patientdetails);
    }
    else{
      this.router.navigate(['/home']); //else call api
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
  oldServiceDetails:{};
  showServiceDetails(details){
    console.log("Details component2");
    this.oldServiceDetails=JSON.parse(JSON.stringify( details ));
    this.serviceDetails=this.oldServiceDetails;
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
  cancel(){   
    this.showPatientServiceDetails=false; 
    this.patientdetails=JSON.parse(JSON.stringify( this.oldPatientDetails ));
    this.isEditable=false;
  }
}
