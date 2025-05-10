import { Routes } from '@angular/router';
import { VouchersComponent } from './pages/vouchers/vouchers.component';
import { BuyComponent } from './pages/buy/buy.component';
import { InputQParams } from './model/InputQParams';
import { InputQparamsResolver } from './input-qparams.resolver';
import { InputDataGuard } from './input-data.guard';
import { WrongAccessComponent } from './pages/wrong-access/wrong-access.component';
import { AuthorizedComponent } from './pages/authorized/authorized.component';
import { ModelDataGuard } from './model-data.guard';
import { InputQdataGuard } from './input-qdata.guard';

export const routes: Routes = [
    { 
        path: '', 
        component: VouchersComponent,
        canActivate: [
            InputQdataGuard
        ],
        resolve: {
            qparam: InputQparamsResolver
        }
    },
    { 
        path: 'vouchers', 
        component: VouchersComponent,
        canActivate: [
            InputDataGuard
        ],
        resolve: {
            qparam: InputQparamsResolver
        }
    },
    // { 
    //     path: '', 
    //     component: BuyComponent,
    //     // canActivate: [
    //     //     InputDataGuard
    //     // ],
    //     resolve: {
    //         qparam: InputQparamsResolver
    //     }
    // },
    { 
        path: 'buy', 
        component: BuyComponent,
        canActivate: [
            ModelDataGuard
        ],
        resolve: {
            qparam: InputQparamsResolver
        }
    },
    // { 
    //     path: 'authorized', //?
    //     component: AuthorizedComponent,
    //     resolve: {
    //         qparam: InputQparamsResolver
    //     }
    // },
    { 
        path: 'wrong', 
        component: WrongAccessComponent
    },
    { 
        path: '*', 
        component: WrongAccessComponent
    }
];
