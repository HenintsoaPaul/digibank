import {Component, inject, input} from '@angular/core';
import {Skeleton} from 'primeng/skeleton';
import {TableModule} from 'primeng/table';
import {BankAccount} from '../models/bank-account.model';
import {Button} from 'primeng/button';
import {Router} from '@angular/router';

@Component({
  selector: 'app-bank-account-list',
  imports: [
    Skeleton,
    TableModule,
    Button,
  ],
  templateUrl: './bank-account-list.html',
})
export class BankAccountList {
  loadingAccounts = input.required<boolean>();
  bankAccounts = input.required<BankAccount[]>();

  router = inject(Router);

  viewAccount(id: string) {
    this.router.navigate(['/accounts', id]);
  }
}
