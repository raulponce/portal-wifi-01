import { Injectable } from '@angular/core';
import {
  Router, Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';
import { Observable, of } from 'rxjs';
import { InputQParams, QParamsType } from './model/InputQParams';

@Injectable({
  providedIn: 'root'
})
export class InputQparamsResolver implements Resolve<InputQParams> {

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<InputQParams> {
    let tplinkOmada_t : string|null = route.queryParamMap.get('t')
    let tplinkOmada_site: string|null = route.queryParamMap.get('site')
    let tplinkOmada_apMac: string|null = route.queryParamMap.get('apMac')
    let tplinkOmada_ssidName: string|null = route.queryParamMap.get('ssidName')
    let tplinkOmada_radioId: string|null = route.queryParamMap.get('radioId')

    let ciscoMeraki_baseGrantUrl: string|null = route.queryParamMap.get('base_grant_url')    
    let ciscoMeraki_nodeId: string|null = route.queryParamMap.get('node_id')
    let ciscoMeraki_nodeMac: string|null = route.queryParamMap.get('node_mac')
    let ciscoMeraki_gatewayId: string|null = route.queryParamMap.get('gateway_id')

    let shared_mac: string|null = null
    let shared_clientIp: string|null = null
    let shared_redirectUrl: string|null = null

    let tipo : QParamsType = QParamsType.EMPTY
    // console.log('ciscoMeraki_nodeId:'+ciscoMeraki_nodeId)
    // console.log('ciscoMeraki_gatewayId:'+ciscoMeraki_gatewayId)
    // console.log('tplinkOmada_site:'+tplinkOmada_site)
    // console.log('tplinkOmada_ssidName:'+tplinkOmada_ssidName)
    if (ciscoMeraki_nodeId != null && ciscoMeraki_gatewayId != null) { 
      tipo = QParamsType.CISCO_MERAKI
      shared_mac = route.queryParamMap.get('client_mac')
      shared_clientIp = route.queryParamMap.get('client_ip')
      //shared_redirectUrl = route.queryParamMap.get('user_continue_url')
      shared_redirectUrl = ciscoMeraki_baseGrantUrl
      // console.log('MERAKI shared_clientIp:'+shared_clientIp)
      // console.log('MERAKI shared_redirectUrl:'+shared_redirectUrl)
    } else if (tplinkOmada_site != null && tplinkOmada_ssidName != null) { 
      tipo = QParamsType.TPLINK_OMADA
      shared_mac = route.queryParamMap.get('clientMac')
      shared_clientIp = route.queryParamMap.get('clientIp')
      shared_redirectUrl = route.queryParamMap.get('redirectUrl')
      // console.log('OMADA shared_clientIp:'+shared_clientIp)
      // console.log('OMADA shared_redirectUrl:'+shared_redirectUrl)
    }

    let shared_mac_normalizado : string|null = null
    if (shared_mac != null) {
      shared_mac_normalizado = shared_mac.replaceAll('-', ':')
    }

    let result: InputQParams = {
      type: tipo,

      t: tplinkOmada_t != null?Number(tplinkOmada_t):null,
      site: tplinkOmada_site,
      apMac: tplinkOmada_apMac,
      ssidName: tplinkOmada_ssidName,
      radioId: tplinkOmada_radioId != null?Number(tplinkOmada_radioId):null,

      baseGrantUrl: ciscoMeraki_baseGrantUrl,
      nodeId: ciscoMeraki_nodeId != null?Number(ciscoMeraki_nodeId):null,
      nodeMac: ciscoMeraki_nodeMac,
      gatewayId: ciscoMeraki_gatewayId != null?Number(ciscoMeraki_gatewayId):null,

      clientMac: shared_mac,
      clientMacNormalizado: shared_mac_normalizado,
      clientIp: shared_clientIp,
      redirectUrl: shared_redirectUrl
    }
    console.log("InputQParam resolve:")
    console.log(result)

    return of(result)
  }
}
