package com.tsoa.digibank.mappers;

import com.tsoa.digibank.data.dtos.*;
import com.tsoa.digibank.data.dtos.bankaccount.*;
import com.tsoa.digibank.data.dtos.operation.*;
import com.tsoa.digibank.data.models.*;
import com.tsoa.digibank.data.models.bankaccount.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * Swap from entities to DTOs (vice versa).
 */
@Service
public class AppMapper {
    // --- Customer Mappings ---
    public CustomerDTO fromCustomer(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }

    public Customer fromCustomerDTO(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    // --- SavingAccount Mappings ---
    public SavingBankAccountDTO fromSavingBankAccount(SavingAccount savingAccount) {
        SavingBankAccountDTO savingBankAccountDTO = new SavingBankAccountDTO();
        BeanUtils.copyProperties(savingAccount, savingBankAccountDTO);
        savingBankAccountDTO.setCustomerDTO(fromCustomer(savingAccount.getCustomer()));
        savingBankAccountDTO.setType(savingAccount.getClass().getSimpleName());
        return savingBankAccountDTO;
    }

    // --- CurrentAccount Mappings ---
    public CurrentBankAccountDTO fromCurrentBankAccount(CurrentAccount currentAccount) {
        CurrentBankAccountDTO currentBankAccountDTO = new CurrentBankAccountDTO();
        BeanUtils.copyProperties(currentAccount, currentBankAccountDTO);
        currentBankAccountDTO.setCustomerDTO(fromCustomer(currentAccount.getCustomer()));
        currentBankAccountDTO.setType(currentAccount.getClass().getSimpleName());
        return currentBankAccountDTO;
    }

    // --- AccountOperation Mappings ---
    public AccountOperationDTO fromAccountOperation(AccountOperation accountOperation) {
        AccountOperationDTO accountOperationDTO = new AccountOperationDTO();
        BeanUtils.copyProperties(accountOperation, accountOperationDTO);
        return accountOperationDTO;
    }
}
