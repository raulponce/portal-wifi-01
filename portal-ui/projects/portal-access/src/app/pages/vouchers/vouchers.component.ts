import { Component, signal } from '@angular/core';
import { ModelService } from '../../model.service';
import { ActivatedRoute } from '@angular/router';
import { InputQParams } from '../../model/InputQParams';
// import { VoucherComponent } from "../../components/voucher/voucher.component";
import { VoucherCard } from '../../model/VoucherCard';
import { VoucherPriceCurrency, VoucherTimeUnit, VoucherType } from '../../model/Voucher';
import { CommonModule } from '@angular/common';
import { MatToolbarModule } from '@angular/material/toolbar'
import { MatCardModule } from '@angular/material/card'
import { MatButtonModule } from '@angular/material/button'

@Component({
  selector: 'app-vouchers',
  // imports: [VoucherComponent, CommonModule, MatCardModule, MatToolbarModule, MatButtonModule],
  imports: [CommonModule, MatCardModule, MatToolbarModule, MatButtonModule],
  templateUrl: './vouchers.component.html',
  styleUrl: './vouchers.component.css',
})
export class VouchersComponent {

  inputData : InputQParams = new InputQParams();

  voucherCards = signal<VoucherCard[]>([]);

  constructor(private model: ModelService, private activatedRoute: ActivatedRoute) {
    this.fillCards()
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

  private fillCards() {
    const cards : VoucherCard[] = []
    for (let i=0; i<5; i++) {
      cards.push({
        urlImagen: 'https://mdbcdn.b-cdn.net/img/new/standard/nature/111.webp',
        voucher: {
          type: VoucherType.BY_TIME,
          vuid: "vuid-"+i,
          price: {
            currency: VoucherPriceCurrency.USD,
            cost: 10.12,
          },
          data: {
            timeUnit: VoucherTimeUnit.MINUTE,
            value: 15
          }
        }
      })
    }
    this.voucherCards.set(cards)
  }

  public btnIngresar() {
    console.log("click");
  }

}
