import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, GuardResult, MaybeAsync, Router, RouterStateSnapshot } from '@angular/router';
import { ModelService } from './model.service';

@Injectable({
  providedIn: 'root'
})
export class InputQdataGuard implements CanActivate {

  constructor(private router:Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): MaybeAsync<GuardResult> {
    let tplinkOmada_mac: string|null = route.queryParamMap.get('clientMac')
    let tplinkOmada_clientIp: string|null = route.queryParamMap.get('clientIp')
    let tplinkOmada_site: string|null = route.queryParamMap.get('site')
    // let tplinkOmada_redirectUrl: string|null = route.queryParamMap.get('redirectUrl')
    let tplinkOmada_ssidName: string|null = route.queryParamMap.get('ssidName')
    // let tplinkOmada_radioId: string|null = route.queryParamMap.get('radioId')

    // let ciscoMeraki_baseGrantUrl: string|null = route.queryParamMap.get('base_grant_url')
    // let ciscoMeraki_userContinueUrl: string|null = route.queryParamMap.get('user_continue_url')
    // let ciscoMeraki_nodeId: string|null = route.queryParamMap.get('node_id')
    // let ciscoMeraki_nodeMac: string|null = route.queryParamMap.get('node_mac')
    // let ciscoMeraki_gatewayId: string|null = route.queryParamMap.get('gateway_id')
    let ciscoMeraki_mac: string|null = route.queryParamMap.get('client_mac')
    let ciscoMeraki_clientIp: string|null = route.queryParamMap.get('client_ip')

    
    if (ciscoMeraki_mac != null && ciscoMeraki_clientIp != null) { 
      console.log("Access valid Cisco Meraki ["+ciscoMeraki_mac+"] ["+ciscoMeraki_clientIp+"]");      
      return true        
    } else if (tplinkOmada_mac != null && tplinkOmada_clientIp != null && tplinkOmada_site != null && tplinkOmada_ssidName != null) { 
      console.log("Access valid TPLink Omada ["+tplinkOmada_mac+"] ["+tplinkOmada_clientIp+"] ["+tplinkOmada_site+"] ["+tplinkOmada_ssidName+"]");      
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
