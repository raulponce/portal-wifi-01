import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, GuardResult, MaybeAsync, Router, RouterStateSnapshot } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class InputDataGuard implements CanActivate {

  constructor(private router:Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): MaybeAsync<GuardResult> {
    let mac: string|null = route.queryParamMap.get('clientMac')
    if (mac != null) {
      console.log("Access success");
      return true
    } else {
      console.log("Access deny");
      this.router.navigate(['/buy']);
      return false
    }
  }
  
}
