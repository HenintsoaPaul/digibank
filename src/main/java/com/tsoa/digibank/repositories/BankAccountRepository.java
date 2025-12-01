package com.tsoa.digibank.repositories;

import com.tsoa.digibank.data.models.Customer;
import com.tsoa.digibank.data.models.bankaccount.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
    List<BankAccount> findBankAccountsByCustomer(Customer customer);
}
