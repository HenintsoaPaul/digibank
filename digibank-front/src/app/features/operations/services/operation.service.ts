import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../../../env/environment';
import {Operation, OperationReq} from '../models/operation.model';

@Injectable({
  providedIn: 'root',
})
export class OperationService {
  constructor(private http: HttpClient) {
  }

  public getOperationsByCustomer(customerId: string): Observable<Operation[]> {
    return this.http.get<Operation[]>(environment.backendHost + "/accounts/" + customerId + "/operations");
  }

  public create(operationReq: OperationReq): Observable<Operation> {
    const url = environment.backendHost + "/operations";
    return this.http.post<Operation>(url, operationReq);
  }
}
