import {Component, computed, inject} from '@angular/core';
import {Primenglogo} from '../logo/primenglogo/primenglogo';
import {ButtonModule} from 'primeng/button';
import {CommonModule} from '@angular/common';
import {LayoutService} from '../../core/layout.service';

@Component({
  selector: 'app-topbar',
  imports: [
    Primenglogo,
    ButtonModule,
    CommonModule,
  ],
  templateUrl: './topbar.html',
  host: {
    class: 'topbar'
  }
})
export class Topbar {
  layoutService = inject(LayoutService);

  isDarkMode = computed(() => this.layoutService.appState().darkMode);

  toggleDarkMode() {
    this.layoutService.appState.update((state) => ({
      ...state,
      darkMode: !state.darkMode,
    }));
  }
}
