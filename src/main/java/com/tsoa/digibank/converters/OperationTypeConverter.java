package com.tsoa.digibank.converters;

import com.tsoa.digibank.data.enums.OperationType;
import jakarta.persistence.AttributeConverter;

public class OperationTypeConverter implements AttributeConverter<OperationType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(OperationType accountStatus) {
        return accountStatus == null ? null : accountStatus.getCode();
    }

    @Override
    public OperationType convertToEntityAttribute(Integer code) {
        return OperationType.fromCode(code);
    }
}
