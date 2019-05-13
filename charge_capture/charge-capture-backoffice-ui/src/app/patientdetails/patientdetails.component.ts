import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
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
    private data:DataService,
    private httpClient:HttpClient
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

    //initializing p to one
    pagenumber: number = 1;
    oldPatientDetails={};
    parentIndex:any;
    sort(key){
      this.key = key;
      this.reverse = !this.reverse;
    }

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
    this.httpClient.put('/chargecapture/updatePatientDetail',patientdetails).subscribe((res)=>{
      console.log(res);
  });
  }
  cancel(){   
    this.showPatientServiceDetails=false; 
    this.patientdetails=JSON.parse(JSON.stringify( this.oldPatientDetails ));
    this.isEditable=false;
  }
  tableRowExpanded:boolean=false;
  tableRowIndexExpandedCurr:any;
  selectTableRow(index, member,details){
    if (member == null || member == undefined)
				return;

			if (this.tableRowExpanded === false) {
				this.tableRowExpanded = true;
				this.tableRowIndexExpandedCurr = member.cpdCodes.cpdcode;
				member.isExpanded = true;

			} else if (this.tableRowExpanded === true) {

				if (this.tableRowIndexExpandedCurr == "" || this.tableRowIndexExpandedCurr == member.cpdCodes.cpdcode) {
					this.tableRowExpanded = false;
					this.tableRowIndexExpandedCurr = member.cpdCodes.cpdcode;
					member.isExpanded = false;
				} else {
					for (var i in details) {
						if (details.certificationName == this.tableRowIndexExpandedCurr) {
							details.isExpanded = false;
							break;
						}
					}
					this.tableRowIndexExpandedCurr = member.cpdCodes.cpdcode;
					member.isExpanded = true;

				}
			}
			this.parentIndex = index;
  }
  
}
