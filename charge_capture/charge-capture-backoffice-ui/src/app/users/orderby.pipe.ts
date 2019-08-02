
import { Pipe, PipeTransform, Injectable } from '@angular/core';

@Pipe({  name: 'orderBy' })

@Injectable()
export class OrderrByPipe implements PipeTransform {

  transform(records: Array<any>, args?: any): any {
    
    return records.sort(function(a, b){
        if(args.direction!=undefined){
            if(args.property=="firstName" || args.property=="dateOfBirth"){
                if(a['patientdetail'][args.property] < b['patientdetail'][args.property]){
                    return -1 * args.direction;
                  }
                  else if( a['patientdetail'][args.property] > b['patientdetail'][args.property]){
                    return 1 * args.direction;
                  }
                  else{
                    return 0;
                  }
            }
             if(args.property=="providerFirstName" && a['provider'] && b['provider']){
                if(a['provider']['firstName'] < b['provider']['firstName']){
                    return -1 * args.direction;
                  }
                  else if( a['provider']['firstName'] > b['provider']['firstName']){
                    return 1 * args.direction;
                  }
                  else{
                    return 0;
                  }
             }
             if(args.property=="facility"){
                if(a['patientdetail']['facility']['name'] < b['patientdetail']['facility']['name']){
                    return -1 * args.direction;
                  }
                  else if( a['patientdetail']['facility']['name'] > b['patientdetail']['facility']['name']){
                    return 1 * args.direction;
                  }
                  else{
                    return 0;
                  }
             }
             if(args.property=="dateOfBirth" || args.property=="dateOfService" ||args.property=="status"){
                if(a[args.property] < b[args.property]){
                    return -1 * args.direction;
                  }
                  else if( a[args.property] > b[args.property]){
                    return 1 * args.direction;
                  }
                  else{
                    return 0;
                  }
             }
        }
        
        });
    };
}

