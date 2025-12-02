import {Component, input} from '@angular/core';
import {Skeleton} from 'primeng/skeleton';
import {TableModule} from 'primeng/table';
import {Operation} from '../models/operation.model';

@Component({
  selector: 'app-operation-list',
  imports: [
    Skeleton,
    TableModule,
  ],
  templateUrl: './operation-list.html',
})
export class OperationList {
  loadingOperations = input.required<boolean>();
  operations = input.required<Operation[]>();
}
