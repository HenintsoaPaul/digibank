package com.tsoa.digibank;

import com.tsoa.digibank.data.dtos.*;
import com.tsoa.digibank.data.enums.*;
import com.tsoa.digibank.data.models.*;
import com.tsoa.digibank.data.models.bankaccount.*;
import com.tsoa.digibank.repositories.*;
import com.tsoa.digibank.services.bankaccount.*;
import com.tsoa.digibank.services.customer.*;
import com.tsoa.digibank.services.operation.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class DigibankApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigibankApplication.class, args);
    }

    private static Customer mockCustomer(String name) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(name + "@gmail.com");
        return customer;
    }

//    @Bean
    CommandLineRunner start(CustomerRepository customerRepository,
                            AccountOperationRepository accountOperationRepository,
                            BankAccountRepository bankAccountRepository) {
        return args -> {
            Stream.of("Amina", "Souad", "Mohammed").forEach(
                    name -> {
                        Customer customer = mockCustomer(name);
                        customerRepository.save(customer);
                    }
            );

            customerRepository.findAll().forEach(customer -> {
                double rand = Math.random();

                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setBalance(rand * 90000);
                currentAccount.setCreateAt(new java.util.Date());
                currentAccount.setStatus(AccountStatus.CREATED);
                currentAccount.setCustomer(customer);
                currentAccount.setOverDraft(9000);
                bankAccountRepository.save(currentAccount);

                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setId(UUID.randomUUID().toString());
                savingAccount.setBalance(rand * 90000);
                savingAccount.setCreateAt(new java.util.Date());
                savingAccount.setStatus(AccountStatus.CREATED);
                savingAccount.setCustomer(customer);
                savingAccount.setInterestRate(10);
                bankAccountRepository.save(savingAccount);
            });

            bankAccountRepository.findAll().forEach(bankAccount -> {
                double rand = Math.random();

                for (int i = 0; i < 2; i++) {
                    AccountOperation accountOperation = new AccountOperation();
                    accountOperation.setOperationDate(new java.util.Date());
                    accountOperation.setAmount(rand * 12000);
                    accountOperation.setType(rand > 0.5 ? OperationType.DEBIT : OperationType.CREDIT);
                    accountOperation.setBankAccount(bankAccount);
                    accountOperationRepository.save(accountOperation);
                }
            });

        };
    }
}
