import {Injectable} from '@angular/core';
import {formatNumber} from '@angular/common';

@Injectable({
  providedIn: "root"
})
export class NumberUtil {
  private locale: string = 'fr-FR';

  format(amount: number): string {
    return formatNumber(amount, this.locale);
  }
}
