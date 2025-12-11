import {Pipe, PipeTransform} from '@angular/core';
import {DatePipe} from '@angular/common';

@Pipe({
  name: "dateFormat",
  standalone: true
})
export class DateFormatPipe implements PipeTransform {
  private dateFormat: string = 'd/M/yyyy, hh:mm:ss';

  constructor(private datePipe: DatePipe) {}

  transform(dateStr: string): string {
    const date = new Date(dateStr);
    return this.datePipe.transform(date, this.dateFormat) || "";
  }
}
