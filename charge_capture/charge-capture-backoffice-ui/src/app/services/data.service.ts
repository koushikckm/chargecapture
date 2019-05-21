import { Injectable } from '@angular/core';

@Injectable()
export class DataService {

    data: any = null;
    setData(data) {
        this.data = data;
    }
    getData() {
        return this.data;
    }
}
