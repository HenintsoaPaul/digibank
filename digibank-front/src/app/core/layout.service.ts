import {effect, Injectable, signal, WritableSignal} from '@angular/core';
import {Observable, Subject} from 'rxjs';

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

  appState: WritableSignal<AppState> = signal<AppState>(this._appState);

  _initialized: boolean = false;

  _transitionComplete: WritableSignal<boolean> = signal<boolean>(false);

  _appStateUpdate: Subject<AppState> = new Subject<AppState>();

  constructor() {
    effect(() => {
      const appState = this.appState();
      if (appState) {
        this.onAppStateUpdate();
      }
    });

    effect(() => {
      const state = this.appState();

      if (!this._initialized || !state) {
        this._initialized = true;
        return;
      }

      this._handleDarkModeTransition(state);
    });
  }

  toggleDarkMode(appState?: AppState): void {
    document.documentElement.classList.add('theme-transition');

    const _appState = appState || this.appState();
    if (_appState.darkMode) {
      document.documentElement.classList.add('p-dark');
    } else {
      document.documentElement.classList.remove('p-dark');
    }

    // Remove transition class after animation
    setTimeout(() => {
      document.documentElement.classList.remove('theme-transition');
    }, 300);
  }

  onAppStateUpdate() {
    this._appState = {...this.appState()};
    this._appStateUpdate.next(this.appState());
    this.toggleDarkMode();
  }

  private _handleDarkModeTransition(config: AppState): void {
    if ((document as any)._startViewTransition) {
      this._startViewTransition(config);
    } else {
      this.toggleDarkMode(config);
      this._onTransitionEnd();
    }
  }

  private _startViewTransition(config: AppState): void {
    const transition = (document as any).startViewTransition(() => {
      this.toggleDarkMode(config);
    });

    transition.ready
      .then(() => {
        this._onTransitionEnd();
      })
      .catch(() => {
      });
  }

  private _onTransitionEnd() {
    this._transitionComplete.set(true);
    setTimeout(() => {
      this._transitionComplete.set(false);
    });
  }
}
