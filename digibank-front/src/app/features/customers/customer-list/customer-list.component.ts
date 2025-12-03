import {Component, inject, OnInit} from '@angular/core';
import {IconField} from 'primeng/iconfield';
import {InputIcon} from 'primeng/inputicon';
import {InputText} from 'primeng/inputtext';
import {TableModule} from 'primeng/table';
import {FormsModule} from '@angular/forms';
import {Customer} from '../models/customer.model';
import {CustomerService} from '../services/customer.service';
import {Router} from '@angular/router';
import {Button} from 'primeng/button';
import {Dialog} from 'primeng/dialog';

@Component({
  selector: 'app-customers',
  imports: [
    IconField,
    InputIcon,
    InputText,
    TableModule,
    FormsModule,
    Button,
    Dialog
  ],
  templateUrl: './customer-list.component.html',
})
export class CustomerList implements OnInit {
  readonly listTitle = "Customers";
  nbRows = 5;

  customers: Customer[] = []
  filteredCustomers: Customer[] = [];

  searchQuery = '';
  loading = false;

  customerService = inject(CustomerService);
  router = inject(Router);

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

  viewCustomer(id: number) {
    this.router.navigate(['/customers', id]);
  }

  editCustomer(id: number) {
    this.router.navigate(['/customers', id, 'edit']);
  }

  // -- DIALOG --

  visible = false;

  showDialog() {
    this.visible = true;
  }
}
