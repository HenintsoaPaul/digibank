import { Component, input } from '@angular/core';

@Component({
  selector: 'app-primenglogo',
  templateUrl: './primenglogo.html',
})
export class Primenglogo {
  isDarkMode = input.required<boolean>();
}
