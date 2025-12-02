import {Component, input} from '@angular/core';
import {Skeleton} from 'primeng/skeleton';
import {TableModule} from 'primeng/table';
import {BankAccount} from '../models/bank-account.model';

@Component({
  selector: 'app-bank-account-list',
  imports: [
    Skeleton,
    TableModule,
  ],
  templateUrl: './bank-account-list.html',
})
export class BankAccountList {
  loadingAccounts = input.required<boolean>();
  bankAccounts = input.required<BankAccount[]>();
}
