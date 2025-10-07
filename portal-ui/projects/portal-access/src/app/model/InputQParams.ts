export enum QParamsType {
  EMPTY, TPLINK_OMADA, CISCO_MERAKI
}

export class InputQParams {
  public type : QParamsType = QParamsType.EMPTY

  //TPLink Omada
  public t: number|null = null            //1742766560
  public site: string|null = null         //67d41b41fa1be0473141c355 <siteId>
  public apMac: string|null = null        //50-91-E3-FF-6F-E2
  public ssidName: string|null = null     //auster_wifi
  public radioId:number|null = null       //0  

  //Cisco Meraki
  public baseGrantUrl: string|null = null
  public nodeId: number|null = null
  public nodeMac: string|null = null
  public gatewayId: number|null = null

  //Compartidos
  public clientMac: string|null = null             //B4-6B-FC-D2-7C-5B | B4:6B:FC:D2:7C:5B
  public clientMacNormalizado: string|null = null  //B4:6B:FC:D2:7C:5B always
  public clientIp: string|null = null              //192.168.1.16
  public redirectUrl: string|null = null           //http%3A%2F%2Fwww-msftconnecttest.com%2Fredirect
}