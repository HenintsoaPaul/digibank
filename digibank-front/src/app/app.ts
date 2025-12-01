import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {Topbar} from './shared/topbar/topbar';
import {Footer} from './shared/footer/footer';
import {Customers} from './features/customers/customers';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Topbar, Footer, Customers],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected readonly title = signal('digibank-front');
}
