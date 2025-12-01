import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../../../env/environment';
import {BankAccount} from '../models/bank-account.model';

@Injectable({
  providedIn: 'root',
})
export class BankAccountService {
  _apiUrl: string = environment.backendHost + "/accounts";

  constructor(private http: HttpClient) {
  }

  public getAccounts(): Observable<BankAccount[]> {
    return this.http.get<BankAccount[]>(this._apiUrl)
  }

  public getBankAccountsByCustomer(customerId: number): Observable<BankAccount[]> {
    return this.http.get<BankAccount[]>(environment.backendHost + "/customers/" + customerId + "/accounts");
  }
}
