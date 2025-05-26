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

  // canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {    
  //   // Obtener parámetros usando método robusto para iOS
  //   const params = this.getQueryParams(route, state);
    
  //   console.log('Detected params:', params);
    
  //   // Verificar parámetros requeridos
  //   if (params.clientMac && params.clientIp && params.t && params.site) {
  //     console.log("Access valid");
  //     return true;
  //   } else {
  //     console.log("Access deny");
  //     this.router.navigate(['/wrong']);
  //     return false
  //   }    
  // }

  // private getQueryParams(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): any {
  //   // Método 1: Usar ActivatedRoute normal
  //   let params = { ...route.queryParams };
    
  //   // Método 2: Si estamos en iOS y no hay parámetros, parsear manualmente la URL
  //   if (this.isIOS() && Object.keys(params).length === 0) {
  //     params = this.parseUrlManually();
  //   }
    
  //   // Método 3: Si aún no hay parámetros, usar el state
  //   if (Object.keys(params).length === 0) {
  //     const urlTree = this.router.parseUrl(state.url);
  //     params = { ...urlTree.queryParams };
  //   }
    
  //   return params;
  // }

  // private parseUrlManually(): any {
  //   const url = window.location.href;
  //   const params: any = {};
    
  //   // Extraer query string
  //   const queryString = url.split('?')[1];
  //   if (!queryString) return params;
    
  //   // Parsear parámetros manualmente
  //   queryString.split('&').forEach(param => {
  //     const [key, value] = param.split('=');
  //     if (key && value) {
  //       params[decodeURIComponent(key)] = decodeURIComponent(value);
  //     }
  //   });
    
  //   return params;
  // }

  // private isIOS(): boolean {
  //   return /iPad|iPhone|iPod/.test(navigator.userAgent);
  // }
}
