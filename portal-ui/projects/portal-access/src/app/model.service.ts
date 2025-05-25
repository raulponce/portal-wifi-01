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

  //http://localhost:4200/?clientMac=B4-6B-FC-D2-7C-5B&clientIp=192.168.1.16&t=1742766560&site=67d41b41fa1be0473141c355&redirectUrl=http%3A%2F%2Fwww.msftconnecttest.com%2Fredirect&apMac=50-91-E3-FF-6F-E2&ssidName=auster_wifi&radioId=0
  //http://localhost:18080/?clientMac=B4-6B-FC-D2-7C-5B&clientIp=192.168.1.16&t=1742766560&site=67d41b41fa1be0473141c355&redirectUrl=http%3A%2F%2Fwww.msftconnecttest.com%2Fredirect&apMac=50-91-E3-FF-6F-E2&ssidName=auster_wifi&radioId=0
  
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
