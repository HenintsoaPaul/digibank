import {Customer} from '../../customers/models/customer.model';

export interface BankAccount {
  id: string;
  balance: number;
  status: "CREATED" | "ACTIVATED" | "SUSPENDED";
  type: "CurrentAccount" | "SavingAccount";
  overDraft?: number;
  interestRate?: number;
  createdAt?: Date;
  customerDTO: Customer;
}

// export interface AccountDetails {
//   accountId: string;
//   balance: number;
//   currentPage: number;
//   totalPages: number;
//   pageSize: number;
//   accountOperationDTOS: AccountOperation[];
// }
//
// export interface AccountOperation {
//   id: number;
//   operationDate: Date;
//   amount: number;
//   type: string;
//   description: string;
// }
