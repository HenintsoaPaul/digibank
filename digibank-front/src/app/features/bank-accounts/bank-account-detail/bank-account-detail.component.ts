import {Component, inject, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {BankAccount} from "../../bank-accounts/models/bank-account.model";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {TableModule} from 'primeng/table';
import {Operation, OperationReq, OperationType} from '../../operations/models/operation.model';
import {OperationService} from '../../operations/services/operation.service';
import {OperationList} from '../../operations/operation-list/operation-list';
import {Button} from 'primeng/button';
import {Dialog} from 'primeng/dialog';
import {InputText} from 'primeng/inputtext';

@Component({
  selector: 'app-bank-account-detail',
  imports: [
    ReactiveFormsModule,
    TableModule,
    OperationList,
    Button,
    Dialog,
    InputText,
  ],
  templateUrl: './bank-account-detail.component.html',
})
export class BankAccountDetailComponent implements OnInit {
  bankAccount?: BankAccount = undefined;

  operations: Operation[] = [];
  loadingOperations: boolean = false;

  private route = inject(ActivatedRoute);
  private operationService = inject(OperationService);

  private fb = inject(FormBuilder);
  operationForm!: FormGroup;
  operationTypeList!: OperationType[];

  ngOnInit() {
    // Fetch then set data
    this.route.data.subscribe((data) => {
      this.bankAccount = data['account'] as BankAccount;
      this._fetchCustomerAccounts(this.bankAccount.id);
    })

    // Setup operation dialog form
    this.operationForm = this.fb.group({
      amount: ['500', [Validators.required]],
      type: ['', [Validators.required]],
      description: [''],
    });

    this.operationTypeList = Object.values(OperationType);
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
  dialogTitle = "Add operation";
  dialogVisible = false;

  toggleDialogVisibility() {
    this.dialogVisible = !this.dialogVisible;
  }

  submitDialog() {
    // Prepare request
    const operationReq = new OperationReq(
      this.getFieldValue("amount") as number,
      this.getFieldValue("type") as OperationType,
      this.getFieldValue("description"),
    );
    console.log(operationReq)

    // Send to API
    const response$ = this.operationService.create(operationReq);

    // Show response in modal pop
    // ...
  }

  getFieldValue(field: string): any {
    return this.operationForm.get(field)!.value!;
  }

  isInvalid(field: string): boolean | undefined {
    const isInvalid = this.operationForm.get(field)?.invalid
      && (
        this.operationForm.get(field)?.dirty
        || this.operationForm.get(field)?.touched
      );
    return isInvalid;
  }
}
