import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { DataService } from "../services/data.service";
@Component({
  selector: 'app-patientlist',
  templateUrl: './patientlist.component.html',
  styleUrls: ['../app.component.css']
})
export class PatientListComponent implements OnInit {

  constructor(
    private httpClient: HttpClient,
    private router:Router,
    private data:DataService
  ) { }

  ngOnInit() {
    this.getPatientDetail();
  }

  //sorting
  key: string = 'patientId'; //set default
  reverse: boolean = false;
  sort(key){
    this.key = key;
    this.reverse = !this.reverse;
  }

  //initializing p to one
  pagenumber: number = 1;

  patientdetails:any;
  showLoader:boolean;
  getPatientDetail(){
    this.showLoader=true;
    this.httpClient.get('https://localhost:8553/chargecapture/getPatientDetail').subscribe((res)=>{
      this.showLoader=false;
      this.patientdetails=res;
  });
  }

  getPatientName(patientdetail){
    if(patientdetail.middleName=="" || patientdetail.middleName==null){
      return patientdetail.firstName+" "+patientdetail.lastName;
    }
    else{
      return patientdetail.firstName+" "+patientdetail.middleName+" "+patientdetail.lastName;
    }
  }

  address:String;
  getPatientAddress(patientdetail){
    this.address='';
    if(patientdetail.addressLine1!='' || patientdetail.addressLine1!=null){
      this.address+=patientdetail.addressLine1+" ";
    }
    if(patientdetail.addressLine2!='' || patientdetail.addressLine2!=null){
      this.address+=patientdetail.addressLine2+" ";
    }
    if(patientdetail.city!='' || patientdetail.city!=null){
      this.address+=patientdetail.city+" ";
    }
    if(patientdetail.state!='' || patientdetail.state!=null){
      this.address+=patientdetail.state+" ";
    }
    if(patientdetail.zip!='' || patientdetail.zip!=null){
      this.address+=patientdetail.zip+" ";
    }
    return this.address;
  }

  navigateToDetails(patientdetail){
    this.data.setData(patientdetail);
    this.router.navigate(['/patientdetails']); 
  }
}
