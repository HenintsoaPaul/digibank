import {Routes} from '@angular/router';
import {CustomerList} from '../features/customers/customer-list/customer-list.component';
import {CustomerDetailComponent} from '../features/customers/customer-detail/customer-detail.component';
import {CustomerDetailResolver} from '../features/customers/customer-detail/customer-detail-resolver';

export const routes: Routes = [
  {
    path: 'customers',
    component: CustomerList
  },
  {
    path: 'customers/:id',
    component: CustomerDetailComponent,
    resolve: {
      customer: CustomerDetailResolver
    }
  },
];
