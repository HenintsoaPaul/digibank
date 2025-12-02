import {Component, inject, OnInit} from '@angular/core';
import {Customer} from '../models/customer.model';
import {ActivatedRoute, Router} from '@angular/router';
import {BankAccountService} from '../../bank-accounts/services/bank-account.service';
import {BankAccount} from "../../bank-accounts/models/bank-account.model";
import {ReactiveFormsModule} from '@angular/forms';
import {TableModule} from 'primeng/table';
import {BankAccountList} from '../../bank-accounts/bank-account-list/bank-account-list';

@Component({
  selector: 'app-customer-detail',
  imports: [
    ReactiveFormsModule,
    TableModule,
    BankAccountList,
  ],
  templateUrl: './customer-detail.component.html',
})
export class CustomerDetailComponent implements OnInit {
  customer?: Customer = undefined;

  accounts: BankAccount[] = [];
  loadingAccounts: boolean = false;

  private router = inject(Router);
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

  viewAccount(id: string) {
    this.router.navigate(['/bank-accounts', id]);
  }
}
