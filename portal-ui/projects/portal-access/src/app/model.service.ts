import { Injectable } from '@angular/core';
import { InputQParams } from './model/InputQParams';
import { Voucher, VoucherDataUnit, VoucherPriceCurrency, VoucherStatus, VoucherTimeUnit, VoucherType } from './model/api/Voucher';
import { VoucherCard } from './model/VoucherCard';
import { HttpClient } from '@angular/common/http';
import { LoadingService } from './loading.service';

@Injectable({
  providedIn: 'root'
})
export class ModelService {

  //Omada
  //http://localhost:4200/?clientMac=B4-6B-FC-D2-7C-5B&clientIp=192.168.1.16&t=1742766560&site=67d41b41fa1be0473141c355&redirectUrl=http%3A%2F%2Fwww.msftconnecttest.com%2Fredirect&apMac=50-91-E3-FF-6F-E2&ssidName=auster_wifi&radioId=0
  //http://localhost:18080/?clientMac=B4-6B-FC-D2-7C-5B&clientIp=192.168.1.16&t=1742766560&site=67d41b41fa1be0473141c355&redirectUrl=http%3A%2F%2Fwww.msftconnecttest.com%2Fredirect&apMac=50-91-E3-FF-6F-E2&ssidName=auster_wifi&radioId=0
  //  clientMac=B4-6B-FC-D2-7C-5B & 
  //  clientIp=192.168.1.16 & 
  //  t=1742766560 &
  //  site=67d41b41fa1be0473141c355 &
  //  redirectUrl=http%3A%2F%2Fwww.msftconnecttest.com%2Fredirect &
  //  apMac=50-91-E3-FF-6F-E2 &
  //  ssidName=auster_wifi &
  //  radioId=0

  //Meraki
  //http://localhost:4200/?base_grant_url=https%3A%2F%2Fn143.network-auth.com%2Fsplash%2Fgrant&user_continue_url=http%3A%2F%2Fmeraki.com%2F&node_id=1301936&node_mac=00:18:0a:13:dd:b0&gateway_id=1301936&client_ip=10.162.50.40&client_mac=ff:ff:96:d5:d5
  //https://splashserver/splash?base_grant_url=https%3A%2F%2Fn143.network-auth.com%2Fsplash%2Fgrant&user_continue_url=http%3A%2F%2Fmeraki.com%2F&node_id=1301936&node_mac=00:18:0a:13:dd:b0&gateway_id=1301936&client_ip=10.162.50.40&client_mac=ff:ff:96:d5:d5
  //  base_grant_url=https%3A%2F%2Fn143.network-auth.com%2Fsplash%2Fgrant &
  //  user_continue_url=http%3A%2F%2Fmeraki.com%2F &
  //  node_id=1301936 &
  //  node_mac=00:18:0a:13:dd:b0 &
  //  gateway_id=1301936 &
  //  client_ip=10.162.50.40 &
  //  client_mac=ff:ff:96:d5:d5

  // equivalencias:
  //  clientMac = client_mac
  //  clientIp = client_ip
  //  t = 
  //  site = 
  //  redirectUrl = user_continue_url
  //  apMac = node_mac
  //  ssidName =
  //  radioId =
  //  = base_grant_url
  //  = node_id
  //  = gateway_id

  //Meraki Cloud
  //https://app-8080-8663064976557974773.devenv-prod.us-west-1.devnetcloud.com/click?base_grant_url=https://app-8080-8663064976557974773.devenv-prod.us-west-1.devnetcloud.com/splash/grant&user_continue_url=https://www.google.com&node_mac=1d:2b:b1:59:c9:7f&client_ip=127.0.0.1&client_mac=35:b8:22:a1:80:01
  //  base_grant_url=https://app-8080-8663064976557974773.devenv-prod.us-west-1.devnetcloud.com/splash/grant &
  //  user_continue_url=https://www.google.com &
  //  node_mac=1d:2b:b1:59:c9:7f &
  //  client_ip=127.0.0.1 &
  //  client_mac=35:b8:22:a1:80:01

  // https://n143.network-auth.com/splash/grant/?continue_url=http://ask.co.uk/&duration=3600
  // https://app-8080-8663064976557974773.devenv-prod.us-west-1.devnetcloud.com/splash/grant?continue_url=https://www.google.com

  // excap
  // https://app-8080-916859030810535696.devenv-prod.us-west-1.devnetcloud.com/click?base_grant_url=https://app-8080-916859030810535696.devenv-prod.us-west-1.devnetcloud.com/splash/grant&user_continue_url=https://www.google.com&node_mac=3a:5e:2e:48:14:cf&client_ip=127.0.0.1&client_mac=44:74:8f:8b:1f:d8
  //   base_grant_url=https://app-8080-916859030810535696.devenv-prod.us-west-1.devnetcloud.com/splash/grant &
  //   user_continue_url=https://www.google.com&node_mac=3a:5e:2e:48:14:cf &
  //   client_ip=127.0.0.1 &
  //   client_mac=44:74:8f:8b:1f:d8

  // https://app-8080-916859030810535696.devenv-prod.us-west-1.devnetcloud.com/click?base_grant_url=https://app-8080-916859030810535696.devenv-prod.us-west-1.devnetcloud.com/splash/grant&user_continue_url=https://www.google.com&node_mac=14:38:90:55:e5:ce&client_ip=127.0.0.1&client_mac=47:43:8b:14:b1:ee
  // https://app-8080-916859030810535696.devenv-prod.us-west-1.devnetcloud.com/click?base_grant_url=https://app-8080-916859030810535696.devenv-prod.us-west-1.devnetcloud.com/splash/grant&user_continue_url=https://www.google.com&node_mac=76:53:28:81:5a:6c&client_ip=127.0.0.1&client_mac=33:50:69:a1:5b:66

  // https://app-8080-916859030810535696.devenv-prod.us-west-1.devnetcloud.com/splash/grant?continue_url=https://www.google.com

  public inputData : InputQParams = new InputQParams()
  public voucherSelected : VoucherCard|null = null

  // public defaultUrl : string = 'https://www.google.com'
  public defaultUrl : string = ''

  private DOMAIN_API : string ='/api/v1'
  
  constructor(private http:HttpClient, private loading:LoadingService) { }


  public async getVouchers() : Promise<Voucher[]> {
    this.loading.setLoading(true, "getVouchers");
    return new Promise((resolve, reject) => {
      this.http.get<Voucher[]>(this.DOMAIN_API+'/voucher/list').subscribe({
        next: (vouchers : Voucher[]) => {
          resolve(vouchers)
        },
        error: (err) => {
          reject(err)
        },
        complete: () => {
          this.loading.setLoading(false, "getVouchers");
        },
      })
    })
  }

  public async buyVoucher(voucher:Voucher) : Promise<boolean> {
    this.loading.setLoading(true, "buying");
    return new Promise((resolve, reject) => {      
      this.http.post<boolean>(this.DOMAIN_API+'/voucher/buy', {
          voucher: voucher, 
          omadaQParam: this.inputData
        }).subscribe({
        next: (buyStatus:boolean) => {
          resolve(buyStatus)
        },
        error: (err) => {
          reject(err)
        },
        complete: () => {
          this.loading.setLoading(false, "buying");
        }
      })
    })
  }
}
