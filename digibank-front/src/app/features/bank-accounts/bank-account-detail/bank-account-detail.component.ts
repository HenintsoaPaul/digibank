import {Component, inject, OnInit, signal} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {BankAccount} from "../../bank-accounts/models/bank-account.model";
import {ReactiveFormsModule} from '@angular/forms';
import {TableModule} from 'primeng/table';
import {Operation} from '../../operations/models/operation.model';
import {OperationService} from '../../operations/services/operation.service';
import {OperationList} from '../../operations/operation-list/operation-list';
import {Button} from 'primeng/button';
import {OperationForm} from '../../operations/operation-form/operation-form';

@Component({
  selector: 'app-bank-account-detail',
  imports: [
    ReactiveFormsModule,
    TableModule,
    OperationList,
    Button,
    OperationForm,
  ],
  templateUrl: './bank-account-detail.component.html',
})
export class BankAccountDetailComponent implements OnInit {
  bankAccount?: BankAccount = undefined;

  operations: Operation[] = [];
  loadingOperations: boolean = false;

  private route = inject(ActivatedRoute);
  private operationService = inject(OperationService);

  ngOnInit() {
    // Fetch then set data
    this.route.data.subscribe((data) => {
      this.bankAccount = data['account'] as BankAccount;
      this._fetchCustomerAccounts(this.bankAccount.id);
    })
  }

  private _fetchCustomerAccounts(accountId: string) {
    this.operationService.getOperationsByCustomer(accountId).subscribe({
      next: (operations) => {
        this.operations = operations;
        this.loadingOperations = false;
      },
      error: (err) => {
        console.log(err);
        this.loadingOperations = false;
      }
    });
  }

  // -- DIALOG --

  dialogBtnText = "Add operation";
  dialogVisible = signal(false);

  showDialog() {
    this.dialogVisible.set(true);
  }

  onCloseDialog(_: boolean) {
    this.dialogVisible.set(false);
  }
}
