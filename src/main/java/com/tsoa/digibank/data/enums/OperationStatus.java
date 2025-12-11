package com.tsoa.digibank.data.enums;

import lombok.Getter;

@Getter
public enum OperationStatus {
    PENDING(1, "pending"),
    VALIDATED(2, "validated");

    private final int code;
    private final String label;

    OperationStatus(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public static OperationStatus fromCode(Integer code) {
        if (code == null) return null;
        return switch (code) {
            case 1 -> PENDING;
            case 2 -> VALIDATED;
            default -> throw new IllegalArgumentException("Unknown operationStatus code: " + code);
        };
    }
}
