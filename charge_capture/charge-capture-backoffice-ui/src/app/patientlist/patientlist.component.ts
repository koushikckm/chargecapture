import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { DataService } from "../services/data.service";
import { forEach } from '@angular/router/src/utils/collection';
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
  key: string = 'status'; //set default
  reverse: boolean = false;
  firstNameIconSHow:boolean=true;
  dateOfBirthIconSHow:boolean=true;
  providerFirstNameIconSHow:boolean=true;
  facilityIconSHow:boolean=true;
  dateOfServiceIconSHow:boolean=true;
  cptIconSHow:boolean=true;
  icdIconSHow:boolean=true;
  statusIconSHow:boolean=false;
  previousKey:string='';

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

  //initializing p to one
  pagenumber: number = 1;

  patientdetails:any;
  patientdetailsForListScreen:any;
  patientdetailsForListScreenFiltered:any=[];
  filteredFinalArray:any=[];
  showLoader:boolean;
  getDateFormat(dateString){
    if(dateString!="" && dateString!=null && dateString!=undefined){
      return dateString.substring(5,7)+'-'+dateString.substring(8,10)+'-'+dateString.substring(0,4);
    }
    return "";
  }
  getPatientDetail(){
    this.showLoader=true;
    this.httpClient.get('/chargecapture/getPatientDetail').subscribe((res)=>{
     
      this.showLoader=false;
      this.patientdetails=res;
      this.patientdetailsForListScreen=JSON.parse(JSON.stringify(this.patientdetails));
      for(let i of this.patientdetailsForListScreen){
        if(i.patientServiceDetail!=null && i.patientServiceDetail!=undefined && i.patientServiceDetail.length>0){
          let patientdetail={};
          patientdetail['firstName']=i.firstName;
          patientdetail['lastName']=i.lastName;
          patientdetail['dateOfBirth']=i.dateOfBirth;
          patientdetail['facility']=i.facility;
          for(let j of i.patientServiceDetail){
            j['patientdetail']=patientdetail;
            this.patientdetailsForListScreenFiltered.push(j);
          }
        }
        else{
          const index = this.patientdetailsForListScreen.indexOf(i);
          this.patientdetailsForListScreen.splice(index, 1);
        }
      }
     
      let array=this.filterByProperty(this.patientdetailsForListScreenFiltered,'patientId');
      let filteredArray=[];
      for(let i in array){
        let val=this.filterByProperty(array[i].value,'dateOfService');
        filteredArray.push(val);
      }
      for(let i=0;i<filteredArray.length;i++){
        for(let j=0;j<filteredArray[i].length;j++){
          let cpdValues="";let icdValues="";
          for(let m=0;m<filteredArray[i][j].value.length;m++){
            
            let val=filteredArray[i][j].value;
            for(let k=0;k<val[m].cptCodes.length;k++){
              cpdValues+=val[m].cptCodes[k].cptCodes.cptcode+',';
            }
            for(let k=0;k<val[m].icdCodes.length;k++){
              icdValues+=val[m].icdCodes[k].icdCodes.icdCode+',';
            }
          }
          filteredArray[i][j].value[0]['cpdValues']=cpdValues.replace(/,\s*$/, "");
          filteredArray[i][j].value[0]['icdValues']=icdValues.replace(/,\s*$/, "");
          this.filteredFinalArray.push(filteredArray[i][j].value[0]);
        }
      }
      let pendingReviewArray=[];
      let submittedArray=[];
      let processedArray=[];
      for(let i=0;i<this.filteredFinalArray.length;i++){
        if(this.filteredFinalArray[i].status=="PendingReview"){
          pendingReviewArray.push(this.filteredFinalArray[i]);
        }
        else if(this.filteredFinalArray[i].status=="Submitted"){
          submittedArray.push(this.filteredFinalArray[i]);
        }
        else if(this.filteredFinalArray[i].status=="Processed"){
          processedArray.push(this.filteredFinalArray[i]);
        }
      }
      pendingReviewArray=this.sortByDateOfService(pendingReviewArray);
      submittedArray=this.sortByDateOfService(submittedArray);
      processedArray=this.sortByDateOfService(processedArray);
      this.filteredFinalArray=[,...pendingReviewArray,...submittedArray,...processedArray];
      console.log(pendingReviewArray);
  });
  }
sortByDateOfService(array){
  array.sort((item1,item2)=>{
    if(item1.dateOfService<item2.dateOfService){
      return 1;
    }
    if(item1.dateOfService>item2.dateOfService){
      return -1;
    }
    return 0;
  });
  return array;
}
  filterByProperty(array,prop){
    const groupedCollection = array.reduce((previous, current)=> {
      if(!previous[current[prop]]) {
          previous[current[prop]] = [current];
      } else {
          previous[current[prop]].push(current);
      }

      return previous;
  }, {});
  return Object.keys(groupedCollection).map(key => ({ key, value: groupedCollection[key] }));
  }
  getPatientName(patientdetail){
    if(patientdetail!=null){
      if(patientdetail.middleName==undefined || patientdetail.middleName=="" || patientdetail.middleName==null){
        return patientdetail.firstName+" "+patientdetail.lastName;
      }
      else{
        return patientdetail.firstName+" "+patientdetail.middleName+" "+patientdetail.lastName;
      }
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
    for(let i in this.patientdetails){
      if(patientdetail.patientId==this.patientdetails[i].patientId){
        this.data.setData(this.patientdetails[i]);
        this.router.navigate(['/patientdetails']);
      }
    }
     
  }
}
