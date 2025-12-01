import {Injectable, signal} from '@angular/core';

export interface AppState {
  preset: string;
  primary: string;
  surface: string | undefined | null;
  darkMode: boolean;
}

@Injectable({
  providedIn: 'root',
})
export class LayoutService {
  _appState: AppState = {
    preset: 'Aura',
    primary: 'emerald',
    surface: null,
    darkMode: false,
  };

  appState = signal<AppState>(this._appState);

  toggleDarkMode(appState?: AppState): void {
    const _appState = appState || this.appState();
    if (_appState.darkMode) {
      document.documentElement.classList.add('p-dark');
    } else {
      document.documentElement.classList.remove('p-dark');
    }
  }
}
