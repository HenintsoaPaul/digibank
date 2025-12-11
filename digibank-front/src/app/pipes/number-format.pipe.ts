import {Pipe, PipeTransform} from '@angular/core';
import {formatNumber} from '@angular/common';

@Pipe({
  name: "numberFormat",
  standalone: true
})
export class NumberFormatPipe implements PipeTransform {
  private locale: string = 'fr-FR';

  transform(amount: number): string {
    return formatNumber(amount, this.locale);
  }
}
