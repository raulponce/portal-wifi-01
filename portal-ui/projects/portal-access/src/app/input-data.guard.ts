import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, GuardResult, MaybeAsync, Router, RouterStateSnapshot } from '@angular/router';
import { ModelService } from './model.service';

@Injectable({
  providedIn: 'root'
})
export class InputDataGuard implements CanActivate {

  constructor(private model : ModelService, private router:Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): MaybeAsync<GuardResult> {
    if (this.model.inputData != null && this.model.inputData.clientMac != null) {
      return true;
    }
    return false;
  }
  
}
