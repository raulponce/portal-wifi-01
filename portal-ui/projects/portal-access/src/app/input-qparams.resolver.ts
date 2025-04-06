import { Injectable } from '@angular/core';
import {
  Router, Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';
import { Observable, of } from 'rxjs';
import { InputQParams } from './model/InputQParams';

@Injectable({
  providedIn: 'root'
})
export class InputQparamsResolver implements Resolve<InputQParams> {

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<InputQParams> {

    return of({
      clientMac: route.queryParamMap.get('clientMac'),
      clientIp: route.queryParamMap.get('clientIp'),
      t: Number(route.queryParamMap.get('t')),
      site: route.queryParamMap.get('site'),
      redirectUrl: route.queryParamMap.get('redirectUrl'),
      apMac: route.queryParamMap.get('apMac'),
      ssidName: route.queryParamMap.get('ssidName'),
      radioId: Number(route.queryParamMap.get('radoiId'))
    });
  }
}
