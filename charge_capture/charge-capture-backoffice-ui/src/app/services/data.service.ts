import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable()
export class DataService {

  data:any=null;
  setData(data){
      this.data=data;
  }
  getData(){
      return this.data;
  }

}