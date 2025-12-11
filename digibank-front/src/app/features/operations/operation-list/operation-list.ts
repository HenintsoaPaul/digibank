import {Component, inject, input} from '@angular/core';
import {Skeleton} from 'primeng/skeleton';
import {TableModule} from 'primeng/table';
import {Operation} from '../models/operation.model';
import {DateUtil} from '../../../utils/DateUtil';
import {NumberUtil} from '../../../utils/NumberUtil';

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

  private dateUtil = inject(DateUtil);
  private numberUtil = inject(NumberUtil);

  formatDate(dateStr: string) {
    return this.dateUtil.format(dateStr);
  }

  formatAmount(amount: number) {
    return this.numberUtil.format(amount);
  }
}
