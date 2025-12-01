import {Component, inject} from '@angular/core';
import {Customer} from '../models/customer.model';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-customer-detail',
  imports: [],
  templateUrl: './customer-detail.component.html',
})
export class CustomerDetailComponent {
  readonly customerId!: string;
  readonly customer!: Customer;

  private route = inject(ActivatedRoute);

  constructor() {
    this.customerId = this.route.snapshot.params['id'];
    this.customer = this.route.snapshot.data as Customer;
  }
}
