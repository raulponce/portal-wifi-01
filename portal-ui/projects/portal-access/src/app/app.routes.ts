import { Routes } from '@angular/router';
import { VouchersComponent } from './pages/vouchers/vouchers.component';
import { BuyComponent } from './pages/buy/buy.component';
import { InputQParams } from './model/InputQParams';
import { InputQparamsResolver } from './input-qparams.resolver';
import { InputDataGuard } from './input-data.guard';

export const routes: Routes = [
    { 
        path: '', 
        component: VouchersComponent,
        canActivate: [
            InputDataGuard
        ],
        resolve: {
            qparam: InputQparamsResolver
        }
    },
    { 
        path: 'buy', 
        component: BuyComponent 
    },
    { 
        path: '*', 
        component: VouchersComponent,
        resolve: {
            qparam: InputQparamsResolver
        }
    }
];
