import { Component, signal } from '@angular/core';
import { ModelService } from '../../model.service';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { InputQParams, QParamsType } from '../../model/InputQParams';
// import { VoucherComponent } from "../../components/voucher/voucher.component";
import { VoucherCard } from '../../model/VoucherCard';
import { Voucher, VoucherDataUnit, VoucherPriceCurrency, VoucherStatus, VoucherTimeUnit, VoucherType } from '../../model/api/Voucher';
import { CommonModule } from '@angular/common';
// import { MatToolbarModule } from '@angular/material/toolbar'
// import { MatCardModule } from '@angular/material/card'
// import { MatButtonModule } from '@angular/material/button'

@Component({
  selector: 'app-vouchers',
  // imports: [VoucherComponent, CommonModule, MatCardModule, MatToolbarModule, MatButtonModule],
  // imports: [CommonModule, MatCardModule, MatToolbarModule, MatButtonModule],
  imports: [CommonModule],
  templateUrl: './vouchers.component.html',
  styleUrl: './vouchers.component.css',
})
export class VouchersComponent {

  inputData : InputQParams = new InputQParams();

  voucherCards = signal<VoucherCard[]>([]);

  constructor(private model: ModelService, private activatedRoute: ActivatedRoute, private router: Router) {
    this.fillCards()
  }

  ngOnInit() {
    this.activatedRoute.data.subscribe( ({qparam}) => {
      this.inputData = qparam
      if (this.inputData.type != QParamsType.EMPTY) {
        this.model.inputData = this.inputData;
        console.log("New access")
      } else {
        this.inputData = this.model.inputData;
        console.log("Reusing access")
      }
      console.log("Data Access:")
      console.log(this.inputData)
    })
  }

  private fillCards() {
    const cards : VoucherCard[] = []
    this.model.getVouchers().then((vouchers) => {
      vouchers.forEach((voucher) => {
        cards.push({
          voucher: voucher
        })
      })
    })
    this.voucherCards.set(cards)
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

  public btnComprar(item : VoucherCard) {
    // console.log("click");
    this.model.voucherSelected = item;
    this.router.navigate(['/buy']);
  }

}
