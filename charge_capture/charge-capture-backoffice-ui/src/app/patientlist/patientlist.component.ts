import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { DataService } from '../services/data.service';
@Component({
  selector: 'app-patientlist',
  templateUrl: './patientlist.component.html',
  styleUrls: ['../app.component.css']
})
export class PatientListComponent implements OnInit {

  constructor(
    private httpClient: HttpClient,
    private router: Router,
    private data: DataService
  ) { }
  key: any = 'patientId';
  reverse: any = false;
  pagenumber: any = 1;
  patientdetails: any;
  showLoader: any;
  address: any;

  ngOnInit() {
    this.getPatientDetail();
  }

  sort(key) {
    this.key = key;
    this.reverse = !this.reverse;
  }

  getPatientDetail() {
    this.showLoader = true;
    this.httpClient.get('/chargecapture/getPatientDetail').subscribe((res) => {
      this.showLoader = false;
      this.patientdetails = res;
    });
  }

  getPatientName(patientdetail) {
    if (patientdetail.middleName === '' || patientdetail.middleName === null) {
      return patientdetail.firstName + ' ' + patientdetail.lastName;
    } else {
      return patientdetail.firstName + ' ' + patientdetail.middleName + ' ' + patientdetail.lastName;
    }
  }

  getPatientAddress(patientdetail) {
    this.address = '';
    if (patientdetail.addressLine1 !== '' || patientdetail.addressLine1 !== null) {
      this.address += patientdetail.addressLine1 + ' ';
    }
    if (patientdetail.addressLine2 !== '' || patientdetail.addressLine2 !== null) {
      this.address += patientdetail.addressLine2 + ' ';
    }
    if (patientdetail.city !== '' || patientdetail.city !== null) {
      this.address += patientdetail.city + ' ';
    }
    if (patientdetail.state !== '' || patientdetail.state !== null) {
      this.address += patientdetail.state + ' ';
    }
    if (patientdetail.zip !== '' || patientdetail.zip !== null) {
      this.address += patientdetail.zip + ' ';
    }
    return this.address;
  }

  navigateToDetails(patientdetail) {
    this.data.setData(patientdetail);
    this.router.navigate(['/patientdetails']);
  }
}
