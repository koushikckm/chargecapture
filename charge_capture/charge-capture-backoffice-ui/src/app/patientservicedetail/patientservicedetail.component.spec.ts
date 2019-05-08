import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientservicedetailComponent } from './patientservicedetail.component';

describe('PatientservicedetailComponent', () => {
  let component: PatientservicedetailComponent;
  let fixture: ComponentFixture<PatientservicedetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PatientservicedetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientservicedetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
