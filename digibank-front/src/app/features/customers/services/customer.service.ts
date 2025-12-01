import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Customer} from '../models/customer.model';
import {Observable} from 'rxjs';
import {environment} from '../../../../env/environment';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  _apiUrl: string = environment.backendHost + "/customers";

  constructor(private http: HttpClient) {
  }

  public getCustomers(): Observable<Customer[]> {
    return this.http.get<Customer[]>(this._apiUrl)
  }

  public searchCustomers(keyword: String): Observable<Customer[]> {
    return this.http.get<Customer[]>(this._apiUrl + "/search?keyword=" + keyword)
  }
}
