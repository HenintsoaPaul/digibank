import {Routes} from '@angular/router';
import {Accounts} from '../features/accounts/accounts';
import {Customers} from '../features/customers/customers';

export const routes: Routes = [
  {
    path: 'accounts',
    component: Accounts
  },
  {
    path: 'customers',
    component: Customers
  },
];
