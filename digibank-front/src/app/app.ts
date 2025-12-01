import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {Topbar} from './shared/topbar/topbar';
import {Footer} from './shared/footer/footer';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Topbar, Footer],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected readonly title = signal('digibank-front');
}
