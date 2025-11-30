package com.tsoa.digibank.data.dtos.bankaccount;

import com.tsoa.digibank.data.dtos.CustomerDTO;
import com.tsoa.digibank.data.enums.AccountStatus;
import lombok.Data;

import java.sql.Date;


@Data
public class BankAccountDTO {
    private String id;
    private double balance;
    private Date createAt;
    private AccountStatus status;
    private CustomerDTO customerDTO;
    private String type;
}
