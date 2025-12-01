import {Component} from '@angular/core';
import {IconField} from 'primeng/iconfield';
import {InputIcon} from 'primeng/inputicon';
import {InputText} from 'primeng/inputtext';
import {PrimeTemplate} from 'primeng/api';
import {ReactiveFormsModule} from '@angular/forms';
import {TableModule} from 'primeng/table';

@Component({
  selector: 'app-accounts',
  imports: [
    IconField,
    InputIcon,
    InputText,
    PrimeTemplate,
    ReactiveFormsModule,
    TableModule
  ],
  templateUrl: './account-list.component.html',
})
export class AccountList {
  readonly listTitle = "Accounts List";
  loading: boolean = false;
}
