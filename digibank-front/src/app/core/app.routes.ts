import {Routes} from '@angular/router';
import {CustomerList} from '../features/customers/customer-list/customer-list.component';
import {CustomerDetail} from '../features/customers/customer-detail/customer-detail';

export const routes: Routes = [
  {
    path: 'customers',
    component: CustomerList
  },
  {
    path: 'customers/:id',
    component: CustomerDetail
  },
];
