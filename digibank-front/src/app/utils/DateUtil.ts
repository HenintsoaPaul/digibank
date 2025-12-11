import {inject, Injectable} from '@angular/core';
import {DatePipe, formatDate} from '@angular/common';

@Injectable({
  providedIn: "root"
})
export class DateUtil {
  private dateFormat: string = 'd/M/yyyy, hh:mm:ss';

  private datePipe = inject(DatePipe);

  format(dateStr: string): string {
    const date = new Date(dateStr);
    return this.datePipe.transform(date, this.dateFormat) || "";
  }
}
