import {Component, inject} from '@angular/core';
import {Customer} from '../models/customer.model';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-customer-detail',
  imports: [],
  templateUrl: './customer-detail.component.html',
})
export class CustomerDetailComponent {
  customerId: string = '';
  customer?: Customer = undefined;

  private route = inject(ActivatedRoute);

  constructor() {
    this.route.data.subscribe((data) => {
      this.customer = data['customer'];
    })

    this.route.params.subscribe((params) => {
      this.customerId = params['id'] as string;
    })
  }
}
