import { Component } from '@angular/core';
import { InputQParams } from '../../model/InputQParams';
import { ModelService } from '../../model.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { VoucherCard } from '../../model/VoucherCard';
import { Voucher, VoucherDataUnit, VoucherPriceCurrency, VoucherTimeUnit, VoucherType } from '../../model/api/Voucher';

@Component({
  selector: 'app-buy',
  imports: [CommonModule, MatCardModule, MatToolbarModule, MatButtonModule],
  templateUrl: './buy.component.html',
  styleUrl: './buy.component.css'
})
export class BuyComponent {

  inputData : InputQParams = new InputQParams();

  card: VoucherCard = new VoucherCard()

  constructor(private model: ModelService, private activatedRoute: ActivatedRoute, private router: Router) {
    this.card = this.model.voucherSelected!;
  }

  ngOnInit() {
    this.activatedRoute.data.subscribe( ({qparam}) => {
      this.inputData = qparam
      if (this.inputData.clientMac != null) {
        this.model.inputData = this.inputData;
      } else {
        this.inputData = this.model.inputData;
      }
    })
  }

  public getClassByType(item : VoucherCard) : string {
    if (item.voucher.type == VoucherType.BY_TIME && item.voucher.price.currency != VoucherPriceCurrency.FREE) {
      return 'card time';
    }
    if (item.voucher.type == VoucherType.BY_DATA && item.voucher.price.currency != VoucherPriceCurrency.FREE) {
      return 'card data';
    }
    if (item.voucher.price.currency == VoucherPriceCurrency.FREE) {
      return 'card free';
    }
    return 'card';
  }

  public getData(item : Voucher) : string {
    if (item.type == VoucherType.BY_TIME) {
      var res : string = ''+item.data.value;
      switch (item.data.unit) {
        case VoucherTimeUnit.DAY:
          res = res + ' Days';
          break;
        case VoucherTimeUnit.HOUR:
          res = res + ' Hours';
          break;
        case VoucherTimeUnit.MINUTE:
          res = res + ' Minutes';
          break;
      } 
      return res;
      //return item.data.unit + ' ' + item.data.value;
    } else if (item.type == VoucherType.BY_DATA) {
      var res : string = ''+item.data.value;
      switch (item.data.unit) {
        case VoucherDataUnit.GIGAS:
          res = res + '  Gigas';
          break;
        case VoucherDataUnit.MEGAS:
          res = res + ' Megas';
          break;
      } 
      return res;
      //return item.data.unit + ' ' + item.data.value;
    }
    return '';
  }

  public getCost(item : Voucher) : string {
    if (item.price.currency == VoucherPriceCurrency.FREE) {
      return 'Free';
    } else if (item.price.currency == VoucherPriceCurrency.USD) {
      return 'u$s ' + item.price.cost;
    } else if (item.price.currency == VoucherPriceCurrency.ARS) {
      return '$ ' + item.price.cost;
    }
    return '';
  }

  public onCancel() {
    this.model.voucherSelected = null;
    this.router.navigate(['/vouchers']);
  }

  public onConfirm() {
    this.model.buyVoucher(this.model.voucherSelected!.voucher).then((status) => {
      if (status) {
        this.model.voucherSelected = null;
        console.log("buy!")
        let urlToGo : string = this.model.inputData.redirectUrl != null ? this.model.inputData.redirectUrl : this.model.defaultUrl 
        console.log("go to: "+urlToGo)
        window.location.href = urlToGo
    
      } else {
        alert("Pago rechazado")
        this.router.navigate(['/vouchers'])
      }
    }).catch(() => {
      this.router.navigate(['/wrong'])
    })
  }

}
