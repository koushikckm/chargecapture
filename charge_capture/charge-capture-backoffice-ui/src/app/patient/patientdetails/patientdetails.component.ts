import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { DataService } from "../../services/data.service";
import { DatePipe } from '@angular/common';
import * as moment from 'moment';
import { Constants } from '../../shared/constants';
import { ActivatedRoute } from "@angular/router";
declare var $: any;
@Component({
  selector: 'app-patientdetails',
  templateUrl: './patientdetails.component.html',
  styleUrls: ['./patientdetails.component.css','../../app.component.css']
})
export class PatientdetailsComponent implements OnInit {

  constructor(
    private router: Router,
    private data: DataService,
    private httpClient: HttpClient,
    private datePipe:DatePipe,
    private activatedRoute:ActivatedRoute
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
  highlightedServiceId:any;
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
    console.log(this.activatedRoute.snapshot.paramMap.get("patientId"));
    this.getPatientData(this.activatedRoute.snapshot.paramMap.get("patientId"));
  }

  getPatientData(patientId){
    this.httpClient.post(Constants.GET_PATIENT_DATA,{patientId:patientId}).subscribe((res)=>{
     // console.log(res);
      this.patientdetails=res;
      this.patientdetails.dateOfBirth = new Date(this.patientdetails.dateOfBirth); 
      if(this.data.getData()!=null){
        this.highlightedServiceId=this.data.getData().serviceId;
        console.log("highlightedServiceId--> "+this.highlightedServiceId);
        for(let i in this.patientdetails.patientServiceDetail){
          if(this.highlightedServiceId==this.patientdetails.patientServiceDetail[i].serviceId){
            //console.log("i--> "+i);
            this.pagenumber=Math.ceil(Number(i)/5);
            //if(this.pagenumber>1){}
            this.showServiceDetails(this.patientdetails.patientServiceDetail[i],i,this.patientdetails,'expand',this.pagenumber) ;
          }
        }     
        
      }
    })
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
  itemsPerPage:any=5;
  showServiceDetails(details,index,patientdetails,icon,pagenumber) {    
    
    console.log(pagenumber);
    console.log(index);
    this.oldServiceDetails = JSON.parse(JSON.stringify(details));
    this.serviceDetails = this.oldServiceDetails;
    let arrayIndex=((pagenumber-1)*this.itemsPerPage)+index;//since index in pagination changesn with page number
    if(patientdetails.patientServiceDetail.length==1){
      arrayIndex=0;
    }
    for(let i in patientdetails.patientServiceDetail){
      if(i!=arrayIndex){
        patientdetails.patientServiceDetail[i].showDiagnosisAndProcedures=false;
      }
    }
    if(details.showDiagnosisAndProcedures){
      details.showDiagnosisAndProcedures=false;
    }
    else{
      details.showDiagnosisAndProcedures=true;
    }
    this.showPatientServiceDetails = true;
    this.showPatientServiceDetailsPanel = true;
  }

  save(patientdetails,messageId,serviceId) {
    if (patientdetails.firstName != null && patientdetails.firstName != "") {
      this.isfirstNameValidMsg = null;
      if (patientdetails.lastName != null && patientdetails.lastName != "") {
        this.islastNameValidMsg = null; 
        if (patientdetails.dateOfBirth != null && patientdetails.dateOfBirth != "") {
          this.memberDOBValidationMsg = null;
          this.showLoader=true;          
          patientdetails.dateOfBirth=this.datePipe.transform(patientdetails.dateOfBirth, 'yyyy-MM-dd hh:mm:ss');
         
          // for(var i=0;i<patientdetails.patientServiceDetail.length;i++){
          //   if(patientdetails.patientServiceDetail[i].checked){
          //     patientdetails.serviceIds.push(patientdetails.patientServiceDetail[i].serviceId);
          //   }
          // }
          if(messageId!=null){
             patientdetails.serviceIds=[serviceId];
             this.httpClient.put(Constants.APPROVE_PATIENT_DETAILS, patientdetails).subscribe((res) => {
              this.showLoader=false;
              $('#modelPopUpButton2').click();
              this.indexValue=0;
            });
          }else{
           this.httpClient.put(Constants.UPDATE_PATIENT_DETAILS, patientdetails).subscribe((res) => {
              this.showLoader=false;
              $('#modelPopUpButton').click();
              this.indexValue=0;
            });
            
          }
            
                 
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
  addNewIcdCodeRow(details){
    let newIcdCode={};
    newIcdCode['icdCodes']={};
    newIcdCode['icdCodes']['icdCode']="";
    newIcdCode['icdCodes']['description']="";    
    details.push(newIcdCode);
  }
  addNewCpdCodeRow(details){
    let newCpdCode={};
    newCpdCode['cptCodes']={};
    newCpdCode['cptCodes']['cptcode']="";
    newCpdCode['cptCodes']['description']="";    
    details.push(newCpdCode);
  }
  removeRow(identifier,details,index){
    if(identifier=='icd'){
      details.splice(index,1);
    }
    else if(identifier=='cpd'){
      details.splice(index,1);
    }    
  }
  saveServiceDetails(patientdetails){
    this.showLoader=true;  
    $('#modelPopUpButton2').click();
    this.showLoader=false;
  }
}
