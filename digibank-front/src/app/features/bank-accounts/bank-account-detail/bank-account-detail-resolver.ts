import {ActivatedRouteSnapshot, Resolve} from '@angular/router';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {BankAccountService} from '../services/bank-account.service';
import {BankAccount} from '../models/bank-account.model';

@Injectable({providedIn: 'root'})
export class BankAccountDetailResolver implements Resolve<BankAccount> {
  constructor(private bankAccountService: BankAccountService) {
  }

  resolve(route: ActivatedRouteSnapshot): Observable<BankAccount> {
    const id = route.params['id'] as string;
    return this.bankAccountService.getBankAccountById(id);
  }
}
