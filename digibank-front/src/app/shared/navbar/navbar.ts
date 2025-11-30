import { Component, signal, effect, WritableSignal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {RouterLink, RouterLinkActive} from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink, RouterLinkActive],
  template: `
    <nav class="navbar" role="navigation" aria-label="Main navigation">
      <div class="nav-left">
        <a routerLink="/accounts" routerLinkActive="active" aria-current="page"
           [@navLinkHover]="hoverStates.get('accounts') ? 'hover' : 'default'">
          Accounts
        </a>
        <a routerLink="/customers" routerLinkActive="active" aria-current="page"
           [@navLinkHover]="hoverStates.get('customers') ? 'hover' : 'default'">
          Customers
        </a>
        <a routerLink="/settings" routerLinkActive="active" aria-current="page"
           [@navLinkHover]="hoverStates.get('settings') ? 'hover' : 'default'">
          Settings
        </a>
      </div>

      <div class="nav-right">
        <button class="search-toggle">
          <span class="search-icon">
            <svg viewBox="0 0 24 24" fill="currentColor">
              <path d="M15.5 14h-.79l-.28-.27A6.471 6.471 0 0 0 16 9.5 6.5 6.5 0 1 0 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z"/>
            </svg>
          </span>
          <span class="close-icon">
            <svg viewBox="0 0 24 24" fill="currentColor">
              <path d="M19 6.41L17.59 5 12 10.59 6.41 5 5 6.41 10.59 12 5 17.59 6.41 19 12 13.41 17.59 19 19 17.59 13.41 12z"/>
            </svg>
          </span>
        </button>

<!--        <div class="search-container" [class.active]="isSearchOpen()">-->
<!--          <input-->
<!--            #searchInput-->
<!--            class="search-input"-->
<!--            type="search"-->
<!--            placeholder="Search accounts, customers..."-->
<!--            [(ngModel)]="searchQuery()"-->
<!--            (keyup.enter)="onSearch()"-->
<!--            (blur)="onSearchBlur()"-->
<!--            autocomplete="off"-->
<!--            aria-label="Search">-->
<!--          <button class="search-submit" (click)="onSearch()" type="button">-->
<!--            <svg viewBox="0 0 24 24" fill="currentColor">-->
<!--              <path d="M15.5 14h-.79l-.28-.27A6.471 6.471 0 0 0 16 9.5 6.5 6.5 0 1 0 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z"/>-->
<!--            </svg>-->
<!--          </button>-->
<!--        </div>-->
      </div>
    </nav>
  `,
  styles: [`
    :host {
      display: block;
      font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
    }

    .navbar {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 1rem 2rem;
      background: linear-gradient(135deg,
        rgba(255, 255, 255, 0.6) 0%,
        rgba(255, 255, 255, 0.3) 50%,
        rgba(59, 130, 246, 0.3) 100%);
      backdrop-filter: blur(20px);
      border-bottom: 1px solid rgba(255, 255, 255, 0.2);
      box-shadow: 0 8px 32px rgba(0, 0, 0, 0.1);
      min-height: 70px;
    }

    .nav-left {
      display: flex;
      gap: 2.5rem;
    }

    .nav-left a {
      position: relative;
      color: rgba(0, 0, 0, 0.8);
      text-decoration: none;
      font-weight: 500;
      font-size: 0.95rem;
      padding: 0.75rem 0;
      transition: all 0.2s cubic-bezier(0.4, 0, 0.2, 1);
      border-bottom: 2px solid transparent;
    }

    .nav-left a:hover,
    .nav-left a.active {
      color: #1e40af;
      border-bottom-color: #3b82f6;
      background: rgba(59, 130, 246, 0.1);
      border-radius: 8px;
      padding-left: 1rem;
      padding-right: 1rem;
      text-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
    }

    .nav-right {
      display: flex;
      align-items: center;
      gap: 1rem;
      position: relative;
    }

    .search-toggle {
      width: 48px;
      height: 48px;
      border: none;
      border-radius: 12px;
      background: rgba(255, 255, 255, 0.8);
      backdrop-filter: blur(10px);
      cursor: pointer;
      display: flex;
      align-items: center;
      justify-content: center;
      transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
      box-shadow: 0 4px 14px rgba(0, 0, 0, 0.1);
      color: #374151;
    }

    .search-toggle:hover {
      background: rgba(248, 250, 252, 0.9);
      transform: translateY(-1px);
      box-shadow: 0 8px 25px rgba(0, 0, 0, 0.15);
    }

    .search-toggle.active {
      background: linear-gradient(135deg, #10b981, #059669);
      color: white;
      box-shadow: 0 8px 25px rgba(16, 185, 129, 0.4);
    }

    .search-icon, .close-icon {
      width: 20px;
      height: 20px;
    }

    .search-icon.hidden, .close-icon.hidden {
      opacity: 0;
      visibility: hidden;
    }

    .search-container {
      position: absolute;
      right: 0;
      top: 100%;
      transform: translateY(-10px);
      opacity: 0;
      visibility: hidden;
      transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
      margin-top: 0.5rem;
      min-width: 320px;
    }

    .search-container.active {
      transform: translateY(0);
      opacity: 1;
      visibility: visible;
    }

    .search-input {
      flex: 1;
      padding: 1rem 1.25rem;
      border: 2px solid rgba(255, 255, 255, 0.4);
      border-radius: 12px 0 0 12px;
      font-size: 0.95rem;
      background: rgba(255, 255, 255, 0.95);
      backdrop-filter: blur(10px);
      outline: none;
      transition: all 0.2s ease;
    }

    .search-input:focus {
      border-color: #3b82f6;
      box-shadow: 0 0 0 4px rgba(59, 130, 246, 0.1);
    }

    .search-submit {
      padding: 1rem 1.25rem;
      border: none;
      background: linear-gradient(135deg, #10b981, #059669);
      color: white;
      border-radius: 0 12px 12px 0;
      cursor: pointer;
      transition: all 0.2s ease;
      display: flex;
      align-items: center;
      justify-content: center;
    }

    .search-submit:hover {
      background: linear-gradient(135deg, #059669, #047857);
      transform: translateX(2px);
    }

    .search-submit svg {
      width: 20px;
      height: 20px;
    }

    @media (max-width: 768px) {
      .navbar {
        padding: 1rem;
        flex-direction: column;
        gap: 1rem;
      }

      .nav-left {
        gap: 1.5rem;
      }

      .search-container {
        position: static;
        transform: none;
        min-width: auto;
        width: 100%;
      }

      .search-input {
        border-radius: 12px 0 0 12px;
      }
    }
  `],
  // animations: [
  //   trigger('navLinkHover', [
  //     transition('default => hover', [
  //       style({ transform: 'translateY(-1px)' }),
  //       animate('150ms cubic-bezier(0.4, 0, 0.2, 1)')
  //     ]),
  //     transition('hover => default', [
  //       animate('150ms cubic-bezier(0.4, 0, 0.2, 1)',
  //         style({ transform: 'translateY(0)' }))
  //     ])
  //   ])
  // ]
})
export class Navbar {
  // Angular 21 signals
  // isSearchOpen = signal(false);
  // searchQuery = signal('');

  // Track hover states for animations
  hoverStates = new Map<string, boolean>([
    ['accounts', false],
    ['customers', false],
    ['settings', false]
  ]);

  constructor() {
    // // Effect for auto-close search on escape key
    // effect(() => {
    //   if (this.isSearchOpen()) {
    //     document.addEventListener('keydown', this.handleEscape);
    //   } else {
    //     document.removeEventListener('keydown', this.handleEscape);
    //   }
    // });
  }

  // toggleSearch() {
  //   this.isSearchOpen.update(open => !open);
  //   if (this.isSearchOpen()) {
  //     // Focus input when opened (Angular 21 input binding)
  //     setTimeout(() => {
  //       const input = document.querySelector('.search-input') as HTMLInputElement;
  //       input?.focus();
  //     }, 100);
  //   }
  // }
  //
  // onSearch() {
  //   console.log('Search query:', this.searchQuery());
  //   // Implement search logic here
  // }
  //
  // onSearchBlur() {
  //   // Delay close to allow clicking search button
  //   setTimeout(() => {
  //     if (!this.searchQuery()) {
  //       this.isSearchOpen.set(false);
  //     }
  //   }, 200);
  // }
  //
  // private handleEscape = (event: KeyboardEvent) => {
  //   if (event.key === 'Escape') {
  //     this.isSearchOpen.set(false);
  //     this.searchQuery.set('');
  //   }
  // };

  // Track hover states for animations
  onLinkHover(link: string, hovering: boolean) {
    this.hoverStates.set(link, hovering);
  }
}
