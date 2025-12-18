import {Component, inject, OnInit, output} from '@angular/core';
import {Dialog} from 'primeng/dialog';
import {OperationReq, OperationType} from '../models/operation.model';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {Button} from 'primeng/button';
import {OperationService} from '../services/operation.service';

@Component({
  selector: 'app-operation-form',
  imports: [
    Dialog,
    ReactiveFormsModule,
    Button
  ],
  templateUrl: './operation-form.html',
})
export class OperationForm implements OnInit {
  dialogTitle = "Add operation";
  isVisible  = true;
  closeDialog  = output<boolean>();

  close() {
    this.isVisible = false;
    this.closeDialog.emit(true);
  }

  private operationService = inject(OperationService);
  private fb = inject(FormBuilder);

  operationForm!: FormGroup;
  operationTypeList!: OperationType[];

  ngOnInit() {
    // Setup form controls
    this.operationForm = this.fb.group({
      amount: ['500', [Validators.required]],
      type: ['', [Validators.required]],
      description: [''],
    });

    this.operationTypeList = Object.values(OperationType);
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
