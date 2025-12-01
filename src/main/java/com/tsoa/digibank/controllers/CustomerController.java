package com.tsoa.digibank.controllers;

import com.tsoa.digibank.data.dtos.CustomerDTO;
import com.tsoa.digibank.data.dtos.bankaccount.BankAccountDTO;
import com.tsoa.digibank.exceptions.CustomerNotFoundException;
import com.tsoa.digibank.services.bankaccount.BankAccountService;
import com.tsoa.digibank.services.customer.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/customers")
public class CustomerController {
    private CustomerService customerService;
    private BankAccountService bankAccountService;

    @GetMapping
    public List<CustomerDTO> customers() {
        return customerService.listCustomers();
    }

    @GetMapping("/{id}/accounts")
    public List<BankAccountDTO> getCustomerBankAccounts(@PathVariable(name = "id") Long customerId) throws CustomerNotFoundException {
        return bankAccountService.bankAccountListByCustomer(customerId);
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomer(@PathVariable(name = "id") Long customerId) throws CustomerNotFoundException {
        return customerService.getCustomer(customerId);
    }

    @PostMapping
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.saveCustomer(customerDTO);
    }

    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        customerDTO.setId(id);
        return customerService.updateCustomer(customerDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}
