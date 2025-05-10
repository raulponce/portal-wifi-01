import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, GuardResult, MaybeAsync, Router, RouterStateSnapshot } from '@angular/router';
import { ModelService } from './model.service';

@Injectable({
  providedIn: 'root'
})
export class InputQdataGuard implements CanActivate {

  constructor(private router:Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): MaybeAsync<GuardResult> {
    let mac: string|null = route.queryParamMap.get('clientMac')
    let clientIp: string|null = route.queryParamMap.get('clientIp')
    let site: string|null = route.queryParamMap.get('site')
    let redirectUrl: string|null = route.queryParamMap.get('redirectUrl')
    let ssidName: string|null = route.queryParamMap.get('ssidName')
    let radioId: string|null = route.queryParamMap.get('radioId')
    
    if (mac != null && clientIp != null && site != null && redirectUrl != null && ssidName != null) {
      console.log("Access valid");      
      return true
    } else {
      console.log("Access deny");
      this.router.navigate(['/wrong']);
      return false
    }
  }
}
