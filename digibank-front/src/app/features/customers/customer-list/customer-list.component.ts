import {Component, OnInit} from '@angular/core';
import {IconField} from 'primeng/iconfield';
import {InputIcon} from 'primeng/inputicon';
import {InputText} from 'primeng/inputtext';
import {TableModule} from 'primeng/table';
import {FormsModule} from '@angular/forms';
import {Customer} from '../models/customer.model';
import {CustomerService} from '../services/customer.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-customers',
  imports: [
    IconField,
    InputIcon,
    InputText,
    TableModule,
    FormsModule
  ],
  templateUrl: './customer-list.component.html',
})
export class CustomerList implements OnInit {
  customers: Customer[] = []
  filteredCustomers: Customer[] = [];

  searchQuery = '';
  loading = false;

  ngOnInit() {
    this._fetchData();
  }

  private _fetchData() {
    this.loading = true;

    this.customerService.getCustomers().subscribe({
      next: (data) => {
        this.customers = data;
        this.filteredCustomers = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('Failed to fetch data:', err);
        this.loading = false;
      }
    })
  }

  constructor(private customerService: CustomerService, private router: Router) {
  }

  handleSearch = () => {
    const query = this.searchQuery.trim().toLowerCase();

    this.filteredCustomers = this.customers.filter(
      (customer) =>
        customer.name.toLowerCase().includes(query) ||
        customer.email.toLowerCase().includes(query) ||
        customer.id.toString().includes(query)
    );

    this.loading = true;

    setTimeout(() => {
      this.loading = false;
    }, 300);
  };
}
