import {Component} from '@angular/core';
import {IconField} from 'primeng/iconfield';
import {InputIcon} from 'primeng/inputicon';
import {InputText} from 'primeng/inputtext';
import {TableModule} from 'primeng/table';
import {FormsModule} from '@angular/forms';

export interface Customer {
  id: number;
  name: string;
  email: string;
}

@Component({
  selector: 'app-customers',
  imports: [
    IconField,
    InputIcon,
    InputText,
    TableModule,
    FormsModule
  ],
  templateUrl: './customers.html',
  styleUrl: './customers.scss',
})
export class Customers {
  customers: Customer[] = [
    {
      id: 2499,
      name: 'Laptop Pro',
      email: 'In Stock',
    },
    {
      id: 49,
      name: 'Wireless Mouse',
      email: 'Low Stock',
    },
    {
      id: 699,
      name: 'Monitor 4K',
      email: 'Out of Stock',
    },
    {
      id: 149,
      name: 'Keyboard',
      email: 'In Stock',
    },
  ];

  searchQuery = '';

  loading = false;

  filteredCustomers: any = [];

  constructor() {
    this.filteredCustomers = [...this.customers];
  }

  onSearchChange = () => {
    this.filteredCustomers = this.customers.filter(
      (product) =>
        product.name.toLowerCase().includes(this.searchQuery.toLowerCase()) ||
        product.email
          .toLowerCase()
          .includes(this.searchQuery.toLowerCase()) ||
        product.id.toString().includes(this.searchQuery.toString())
    );

    this.loading = true;

    setTimeout(() => {
      this.loading = false;
    }, 300);
  };
}
