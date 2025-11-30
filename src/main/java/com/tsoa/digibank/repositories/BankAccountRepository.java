package com.tsoa.digibank.repositories;

import com.tsoa.digibank.data.models.bankaccount.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
}
