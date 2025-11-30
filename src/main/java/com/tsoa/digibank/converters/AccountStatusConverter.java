package com.tsoa.digibank.converters;

import com.tsoa.digibank.data.enums.AccountStatus;
import jakarta.persistence.AttributeConverter;

public class AccountStatusConverter implements AttributeConverter<AccountStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(AccountStatus accountStatus) {
        return accountStatus == null ? null : accountStatus.getCode();
    }

    @Override
    public AccountStatus convertToEntityAttribute(Integer code) {
        return AccountStatus.fromCode(code);
    }
}
