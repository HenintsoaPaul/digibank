import {Customer} from '../models/customer.model';
import {ActivatedRouteSnapshot, Resolve} from '@angular/router';
import {Injectable} from '@angular/core';
import {CustomerService} from '../services/customer.service';
import {Observable} from 'rxjs';

@Injectable({providedIn: 'root'})
export class CustomerDetailResolver implements Resolve<Customer> {
  constructor(private customerService: CustomerService) {
  }

  resolve(route: ActivatedRouteSnapshot): Observable<Customer> {
    const id = route.params['id'];
    return this.customerService.getCustomerById(id);
  }
}
