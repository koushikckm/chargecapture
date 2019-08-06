import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { DatePipe } from '@angular/common';
import * as moment from 'moment';
import { HttpClient } from '@angular/common/http';
import { Constants } from '../../shared/constants';
@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent implements OnInit {

  constructor(private route: ActivatedRoute,
    private datePipe:DatePipe,
  private http:HttpClient) { }
  id:any;
  users:any;
  user:any;
  showLoader:boolean=false;
  userShow:boolean=true;
  facilities:any;
  a2eOptions = {
    format: "YYYY-MM-DD",
    maxDate: new Date(),
    ignoreReadonly: true,
    allowInputToggle: true,
  };
  getAllFacility(){
    this.http.get(Constants.GET_ALL_FACILITY).subscribe((res)=>{
      this.facilities=res;
      console.log(this.facilities[0]);
    })
  }
  getUser(){
    for(let i in this.users){
      if(this.users[i]._id==this.id){
        this.user=this.users[i];

        //dummy data to be removed
        this.user.email="test@mail.com";
        this.user.mobilePhone="0987654321";
        // this.user['facility']['name']="River Oaks";
        // this.user['facility']['facilityId']="3";
      }          
    }
  }
  getAge(dateOfBirth: any): number {
    return moment().diff(dateOfBirth, 'years');
  }
  ngOnInit() {
    //jQuery('select').selectpicker();
    this.id = this.route.snapshot.paramMap.get("userid");   
    this.getAllFacility(); 
    this.users=[
      {
        "_id": "5d4415c1d99a0ee88ac585d8",
        "index": 0,
        "firstName": "John",
        "lastName": "Doe",
        "userName": "johnDoe@mail.com",
        "dateOfBirth": "2014-05-23",
        "lastDateOfService": "2014-05-23",
        "status": "Active"
      },
      {
        "_id": "5d4415c1a0fc4f8ec2a35a26",
        "index": 1,
        "firstName": "John",
        "lastName": "Doe",
        "userName": "johnDoe@mail.com",
        "dateOfBirth": "2014-05-23T06:03:26",
        "lastDateOfService": "2014-05-23T06:03:26",
        "status": "Active"
      },
      {
        "_id": "5d4415c1db64612ae8d22081",
        "index": 2,
        "firstName": "John",
        "lastName": "Doe",
        "userName": "johnDoe@mail.com",
        "dateOfBirth": "2014-05-23T06:03:26",
        "lastDateOfService": "2014-05-23T06:03:26",
        "status": "Active"
      },
      {
        "_id": "5d4415c1a46ba02ac7b3de9a",
        "index": 3,
        "firstName": "John",
        "lastName": "Doe",
        "userName": "johnDoe@mail.com",
        "dateOfBirth": "2014-05-23T06:03:26",
        "lastDateOfService": "2014-05-23T06:03:26",
        "status": "Active"
      },
      {
        "_id": "5d4415c13c1bd4a3435c8eb3",
        "index": 4,
        "firstName": "John",
        "lastName": "Doe",
        "userName": "johnDoe@mail.com",
        "dateOfBirth": "2014-05-23T06:03:26",
        "lastDateOfService": "2014-05-23T06:03:26",
        "status": "Active"
      },
      {
        "_id": "5d4415c1d4188d84b3ce13be",
        "index": 5,
        "firstName": "John",
        "lastName": "Doe",
        "userName": "johnDoe@mail.com",
        "dateOfBirth": "2014-05-23T06:03:26",
        "lastDateOfService": "2014-05-23T06:03:26",
        "status": "Active"
      },
      {
        "_id": "5d4415ad8babaa62696eeba0",
        "index": 0,
        "firstName": "John",
        "lastName": "Doe",
        "userName": "johnDoe@mail.com",
        "dateOfBirth": "2014-05-23T06:03:26",
        "lastDateOfService": "2014-05-23T06:03:26",
        "status": "InActive"
      },
      {
        "_id": "5d4415adbbc4a6ed3ec585d1",
        "index": 1,
        "firstName": "John",
        "lastName": "Doe",
        "userName": "johnDoe@mail.com",
        "dateOfBirth": "2014-05-23T06:03:26",
        "lastDateOfService": "2014-05-23T06:03:26",
        "status": "InActive"
      },
      {
        "_id": "5d4415adda0361869921569e",
        "index": 2,
        "firstName": "John",
        "lastName": "Doe",
        "userName": "johnDoe@mail.com",
        "dateOfBirth": "2014-05-23T06:03:26",
        "lastDateOfService": "2014-05-23T06:03:26",
        "status": "InActive"
      },
      {
        "_id": "5d4415ada9e25b8ec706e360",
        "index": 3,
        "firstName": "John",
        "lastName": "Doe",
        "userName": "johnDoe@mail.com",
        "dateOfBirth": "2014-05-23T06:03:26",
        "lastDateOfService": "2014-05-23T06:03:26",
        "status": "InActive"
      },
      {
        "_id": "5d4415ad83255b2885ed8f60",
        "index": 4,
        "firstName": "John",
        "lastName": "Doe",
        "userName": "johnDoe@mail.com",
        "dateOfBirth": "2014-05-23T06:03:26",
        "lastDateOfService": "2014-05-23T06:03:26",
        "status": "InActive"
      },
      {
        "_id": "5d4415ad34cd5d269b03e0ff",
        "index": 5,
        "firstName": "John",
        "lastName": "Doe",
        "userName": "johnDoe@mail.com",
        "dateOfBirth": "2014-05-23T06:03:26",
        "lastDateOfService": "2014-05-23T06:03:26",
        "status": "InActive"
      },
      {
        "_id": "5d4415df2cf71edd27266826",
        "index": 0,
        "firstName": "John",
        "lastName": "Doe",
        "userName": "johnDoe@mail.com",
        "dateOfBirth": "2014-05-23T06:03:26",
        "lastDateOfService": "2014-05-23T06:03:26",
        "status": "Active"
      },
      {
        "_id": "5d4415df1320cf7fe42d6d67",
        "index": 1,
        "firstName": "John",
        "lastName": "Doe",
        "userName": "johnDoe@mail.com",
        "dateOfBirth": "2014-05-23T06:03:26",
        "lastDateOfService": "2014-05-23T06:03:26",
        "status": "Active"
      },
      {
        "_id": "5d4415df2c36c23a90166e21",
        "index": 2,
        "firstName": "John",
        "lastName": "Doe",
        "userName": "johnDoe@mail.com",
        "dateOfBirth": "2014-05-23T06:03:26",
        "lastDateOfService": "2014-05-23T06:03:26",
        "status": "Active"
      },
      {
        "_id": "5d4415df568cd7f5b5aefa40",
        "index": 3,
        "firstName": "John",
        "lastName": "Doe",
        "userName": "johnDoe@mail.com",
        "dateOfBirth": "2014-05-23T06:03:26",
        "lastDateOfService": "2014-05-23T06:03:26",
        "status": "Active"
      },
      {
        "_id": "5d4415df2638f5390fc9b7b9",
        "index": 4,
        "firstName": "John",
        "lastName": "Doe",
        "userName": "johnDoe@mail.com",
        "dateOfBirth": "2014-05-23T06:03:26",
        "lastDateOfService": "2014-05-23T06:03:26",
        "status": "Active"
      },
      {
        "_id": "5d4415df6bcb93bf192e5fa4",
        "index": 5,
        "firstName": "John",
        "lastName": "Doe",
        "userName": "johnDoe@mail.com",
        "dateOfBirth": "2014-05-23T06:03:26",
        "lastDateOfService": "2014-05-23T06:03:26",
        "status": "Active"
      },
      {
        "_id": "5d4415df8c2537567f7df583",
        "index": 6,
        "firstName": "John",
        "lastName": "Doe",
        "userName": "johnDoe@mail.com",
        "dateOfBirth": "2014-05-23T06:03:26",
        "lastDateOfService": "2014-05-23T06:03:26",
        "status": "Active"
      },
      {
        "_id": "5d4415df1f64307b83369849",
        "index": 7,
        "firstName": "John",
        "lastName": "Doe",
        "userName": "johnDoe@mail.com",
        "dateOfBirth": "2014-05-23T06:03:26",
        "lastDateOfService": "2014-05-23T06:03:26",
        "status": "Active"
      },
      {
        "_id": "5d4415df8cbce04db72f229b",
        "index": 8,
        "firstName": "John",
        "lastName": "Doe",
        "userName": "johnDoe@mail.com",
        "dateOfBirth": "2014-05-23T06:03:26",
        "lastDateOfService": "2014-05-23T06:03:26",
        "status": "Active"
      },
      {
        "_id": "5d4415dfaa003d16974ed28e",
        "index": 9,
        "firstName": "John",
        "lastName": "Doe",
        "userName": "johnDoe@mail.com",
        "dateOfBirth": "2014-05-23T06:03:26",
        "lastDateOfService": "2014-05-23T06:03:26",
        "status": "Active"
      },
      {
        "_id": "5d4415df9ba0775dfa4465b1",
        "index": 10,
        "firstName": "John",
        "lastName": "Doe",
        "userName": "johnDoe@mail.com",
        "dateOfBirth": "2014-05-23T06:03:26",
        "lastDateOfService": "2014-05-23T06:03:26",
        "status": "Active"
      },
      {
        "_id": "5d4415df82c98f8fd2c29c66",
        "index": 11,
        "firstName": "John",
        "lastName": "Doe",
        "userName": "johnDoe@mail.com",
        "dateOfBirth": "2014-05-23T06:03:26",
        "lastDateOfService": "2014-05-23T06:03:26",
        "status": "Active"
      }
    ]
    this.getUser();
  }

}
