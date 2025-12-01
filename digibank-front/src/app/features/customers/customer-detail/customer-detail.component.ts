import {Component, inject, OnInit} from '@angular/core';
import {Customer} from '../models/customer.model';
import {ActivatedRoute} from '@angular/router';
import {BankAccountService} from '../../bank-accounts/services/bank-account.service';
import {BankAccount} from "../../bank-accounts/models/bank-account.model";
import {Skeleton} from 'primeng/skeleton';

@Component({
  selector: 'app-customer-detail',
  imports: [
    Skeleton,
  ],
  templateUrl: './customer-detail.component.html',
})
export class CustomerDetailComponent implements OnInit {
  customer?: Customer = undefined;

  accounts: BankAccount[] = [];
  loadingAccounts: boolean = false;

  private route = inject(ActivatedRoute);
  private accountService = inject(BankAccountService);

  ngOnInit() {
    this.route.data.subscribe((data) => {
      this.customer = data['customer'] as Customer;
      this._fetchCustomerAccounts(this.customer.id);
    })
  }

  private _fetchCustomerAccounts(customerId: number) {
    this.accountService.getBankAccountsByCustomer(customerId).subscribe({
      next: (accounts) => {
        this.accounts = accounts;
        this.loadingAccounts = false;
      },
      error: (err) => {
        console.log(err);
        this.loadingAccounts = false;
      }
    });
  }
}
