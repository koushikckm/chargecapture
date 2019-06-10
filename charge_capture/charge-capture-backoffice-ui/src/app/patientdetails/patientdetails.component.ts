import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { DataService } from "../services/data.service";
import { DatePipe } from '@angular/common'
import * as moment from 'moment';
@Component({
  selector: 'app-patientdetails',
  templateUrl: './patientdetails.component.html',
  styleUrls: ['./patientdetails.component.css','../app.component.css']
})
export class PatientdetailsComponent implements OnInit {

  constructor(
    private router: Router,
    private data: DataService,
    private httpClient: HttpClient,
    private datePipe:DatePipe
  ) { }
  patientdetails: any = {};
  patientDetailsShow: boolean = false;
  showPatientServiceDetails: boolean = false;
  showPatientServiceDetailsPanel: boolean = true;
  patientServiceDetailsShow: boolean = true;
  //sorting
  key: string = 'patientId'; //set default
  reverse: boolean = false;

  //initializing p to one
  pagenumber: number = 1;
  oldPatientDetails = {};
  parentIndex: any;
  isfirstNameValidMsg: String = null;
  islastNameValidMsg: String = null;
  memberDOBValidationMsg: String = null;
  showLoader:boolean;
  indexValue:any;
  readonlyFlagICD:boolean=true;
  readonlyFlagCPD:boolean=true;
  statusAll:boolean=false;

  isDesc: boolean = true;
  column: string = 'status';
  direction: number;
  sort(key){
    // this[this.key+'IconSHow']=true;
    // this.key = key;
    // this.reverse = !this.reverse;
    // this[key+'IconSHow']=false;

    this.isDesc = !this.isDesc; //change the direction    
    this.column = key;
    this.direction = this.isDesc ? 1 : -1;
  }


  ngOnInit() {
    this.indexValue=999999;
    if (this.data.getData() != null) {
      this.patientdetails = this.data.getData();
      console.log(this.patientdetails);
      this.oldPatientDetails = JSON.parse(JSON.stringify(this.patientdetails));
      this.patientdetails.dateOfBirth = new Date(this.patientdetails.dateOfBirth);
      this.patientdetails.ssn = this.formatData(this.patientdetails.ssn, 'ssn');
      this.patientdetails.homePhone = this.formatData(this.patientdetails.homePhone, 'homePhone');
      this.patientdetails.workPhone = this.formatData(this.patientdetails.workPhone, 'workPhone');
      this.patientdetails.mobilePhone = this.formatData(this.patientdetails.mobilePhone, 'mobilePhone');
    }
    else {
      this.router.navigate(['/home']); //else call api
    }
  }
  
  formatData(data, identifier) {
    if (data != null && data != "" && data.length > 0) {
      data = this.replaceAll(data, '-', '');
      data = this.replaceAll(data, '(', '');
      data = this.replaceAll(data, ')', '');
      if (identifier == 'ssn') {
        return data.substring(0, 3) + '-' + data.substring(3, 5) + '-' + data.substring(5, 9);
      }
      else {
        return data.substring(0, 3) + '-' + data.substring(3, 6) + '-' + data.substring(6, 10);
      }
    }

  }
  escapeRegExp(str) {
    return str.replace(/([.*+?^=!:${}()|\[\]\/\\])/g, "\\$1");
  }
  replaceAll(str, find, replace) {
    return str.replace(new RegExp(this.escapeRegExp(find), 'g'), replace);
  }
  hidePanel(prop) {
    if (this[prop] == true) {
      this[prop] = false;
    }
    else {
      this[prop] = true;
      if(prop=='showPatientServiceDetailsPanel'){
        this.patientDetailsShow=false;
      }
      else if(prop=='patientDetailsShow'){
        this.showPatientServiceDetailsPanel=false;
      }
    }
  }

  serviceDetails: any = null;
  oldServiceDetails: {};
  showServiceDetails(details) {
    this.oldServiceDetails = JSON.parse(JSON.stringify(details));
    this.serviceDetails = this.oldServiceDetails;
    this.showPatientServiceDetails = true;
    this.showPatientServiceDetailsPanel = true;
  }
  save(patientdetails) {
    if (patientdetails.firstName != null && patientdetails.firstName != "") {
      this.isfirstNameValidMsg = null;
      if (patientdetails.lastName != null && patientdetails.lastName != "") {
        this.islastNameValidMsg = null; 
        if (patientdetails.dateOfBirth != null && patientdetails.dateOfBirth != "") {
          this.memberDOBValidationMsg = null;
          this.showLoader=true;          
          patientdetails.dateOfBirth=this.datePipe.transform(patientdetails.dateOfBirth, 'yyyy-MM-dd hh:mm:ss');
          patientdetails.serviceIds=[];
          for(var i=0;i<patientdetails.patientServiceDetail.length;i++){
            if(patientdetails.patientServiceDetail[i].checked){
              patientdetails.serviceIds.push(patientdetails.patientServiceDetail[i].serviceId);
            }
          }
            this.httpClient.put('/chargecapture/updatePatientDetail', patientdetails).subscribe((res) => {
              this.showLoader=false;
              $('#modelPopUpButton').click();
              this.indexValue=0;
            });
                 
        }
        else {
          this.memberDOBValidationMsg = "Date of Birth is required";
        }
      }
      else {
        this.islastNameValidMsg = "Last Name is required";
      }
    }
    else {
      this.isfirstNameValidMsg = "First Name is required";
    }
  }
  emailValidator() {

  }
  cancel() {
    this.showPatientServiceDetails = false;
    this.patientdetails.dateOfBirth = null;
    this.patientdetails = JSON.parse(JSON.stringify(this.oldPatientDetails));
    this.patientdetails.dateOfBirth = new Date(this.patientdetails.dateOfBirth);
    this.router.navigate(['/home']);
  }
  tableRowExpanded: boolean = false;
  tableRowIndexExpandedCurr: any;
  selectTableRow(index, member, details) {
    if (member == null || member == undefined)
      return;

    if (this.tableRowExpanded === false) {
      this.tableRowExpanded = true;
      this.tableRowIndexExpandedCurr = member.cptCodes.cptcode;
      member.isExpanded = true;

    } else if (this.tableRowExpanded === true) {

      if (this.tableRowIndexExpandedCurr == "" || this.tableRowIndexExpandedCurr == member.cptCodes.cptcode) {
        this.tableRowExpanded = false;
        this.tableRowIndexExpandedCurr = member.cptCodes.cptcode;
        member.isExpanded = false;
      } else {
        for (var i in details) {
          if (details.certificationName == this.tableRowIndexExpandedCurr) {
            details.isExpanded = false;
            break;
          }
        }
        this.tableRowIndexExpandedCurr = member.cptCodes.cptcode;
        member.isExpanded = true;

      }
    }
    this.parentIndex = index;
  }
  phoneNumberRegex = /^[0-9]{3}-[0-9]{3}-[0-9]{4}$/;
  ssnRegex = /^[0-9]{3}-[0-9]{2}-[0-9]{4}$/;
  a2eOptions = {
    format: "MM/DD/YYYY",
    maxDate: new Date(),
    ignoreReadonly: true,
    allowInputToggle: true,
  };

  changeModel(modelData, identifier) {
    if (identifier == "ssn") {
      if (modelData.length == 3 || modelData.length == 6) {
        this.patientdetails[identifier] = modelData + "-";
      }
    }
    else {
      if (modelData.length == 3 || modelData.length == 7) {
        this.patientdetails[identifier] = modelData + "-";
      }
    }

  }

  closeButtonModel() {
    this.router.navigate(['/home']); //else call api
  }

  getAge(dateOfBirth: any): number {
    return moment().diff(dateOfBirth, 'years');
  }

  getServiceDetails(serviceDetail){
   
  }
  selectAll(patientServiceDetails){
      patientServiceDetails.forEach(patientServiceDetail => {
        patientServiceDetail.checked=this.statusAll;
      });
  }
  addNewIcdCodeRow(){
    let newIcdCode={};
    newIcdCode['icdCodes']={};
    newIcdCode['icdCodes']['icdCode']="";
    newIcdCode['icdCodes']['description']="";    
    this.serviceDetails.icdCodes.push(newIcdCode);
  }
  addNewCpdCodeRow(){
    let newCpdCode={};
    newCpdCode['cptCodes']={};
    newCpdCode['cptCodes']['cptcode']="";
    newCpdCode['cptCodes']['description']="";    
    this.serviceDetails.cptCodes.push(newCpdCode);
  }
  removeRow(identifier,serviceDetail,index){
    if(identifier=='icd'){
      this.serviceDetails.icdCodes.splice(index,1);
    }
    else if(identifier=='cpd'){
      this.serviceDetails.cptCodes.splice(index,1);
    }    
  }
  saveServiceDetails(patientdetails){
    this.showLoader=true;  
    $('#modelPopUpButton2').click();
    this.showLoader=false;
  }
}
