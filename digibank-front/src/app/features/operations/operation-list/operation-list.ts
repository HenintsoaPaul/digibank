import {Component, inject, input} from '@angular/core';
import {Skeleton} from 'primeng/skeleton';
import {TableModule} from 'primeng/table';
import {Operation} from '../models/operation.model';
import {DateFormatPipe} from '../../../pipes/date-format.pipe';
import {NumberFormatPipe} from '../../../pipes/number-format.pipe';

@Component({
  selector: 'app-operation-list',
  imports: [
    Skeleton,
    TableModule,
    DateFormatPipe,
    NumberFormatPipe
  ],
  templateUrl: './operation-list.html',
})
export class OperationList {
  loadingOperations = input.required<boolean>();
  operations = input.required<Operation[]>();
}
