import {Component, computed, inject} from '@angular/core';
import {Primenglogo} from '../logo/primenglogo/primenglogo';
import {ButtonModule} from 'primeng/button';
import {CommonModule} from '@angular/common';
import {LayoutService} from '../../core/layout.service';
import {Menubar} from 'primeng/menubar';

@Component({
  selector: 'app-topbar',
  imports: [
    Primenglogo,
    ButtonModule,
    CommonModule,
    Menubar,
  ],
  templateUrl: './topbar.html',
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

  menuItems = [
    {label: 'Home', icon: 'pi pi-fw pi-home', routerLink: '/'},
    {label: 'Customers', icon: 'pi pi-fw pi-users', routerLink: '/customers'},
    {label: 'Accounts', icon: 'pi pi-fw pi-wallet', routerLink: '/accounts'},
    {label: 'Transfers', icon: 'pi pi-fw pi-exchange-alt', routerLink: '/transfers'},
    {label: 'Payments', icon: 'pi pi-fw pi-credit-card', routerLink: '/payments'},
    {label: 'Profile', icon: 'pi pi-fw pi-user', routerLink: '/profile'}
  ];
}
