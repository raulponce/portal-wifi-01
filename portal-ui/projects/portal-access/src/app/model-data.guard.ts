import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, GuardResult, MaybeAsync, Router, RouterStateSnapshot } from '@angular/router';
import { ModelService } from './model.service';

@Injectable({
  providedIn: 'root'
})
export class ModelDataGuard implements CanActivate {

  constructor(private model : ModelService, private router:Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): MaybeAsync<GuardResult> {
    if (this.model.voucherSelected != null) {
      return true;
    } else {
      console.log("Access deny");
      this.router.navigate(['/wrong']);
    }
    return false;
  }
  
}
