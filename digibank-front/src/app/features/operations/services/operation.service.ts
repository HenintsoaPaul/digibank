import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../../../env/environment';
import {Operation} from '../models/operation.model';

@Injectable({
  providedIn: 'root',
})
export class OperationService {
  constructor(private http: HttpClient) {
  }

  public getOperationsByCustomer(customerId: string): Observable<Operation[]> {
    return this.http.get<Operation[]>(environment.backendHost + "/accounts/" + customerId + "/operations");
  }
}
