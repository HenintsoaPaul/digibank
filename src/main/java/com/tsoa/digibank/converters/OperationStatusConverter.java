package com.tsoa.digibank.converters;

import com.tsoa.digibank.data.enums.OperationStatus;
import jakarta.persistence.AttributeConverter;

public class OperationStatusConverter implements AttributeConverter<OperationStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(OperationStatus accountStatus) {
        return accountStatus == null ? null : accountStatus.getCode();
    }

    @Override
    public OperationStatus convertToEntityAttribute(Integer code) {
        return OperationStatus.fromCode(code);
    }
}
