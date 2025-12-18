package com.tsoa.digibank.data.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum OperationType {
    DEBIT(1, "debit"),
    CREDIT(2, "credit");

    private final int code;
    private final String label;

    OperationType(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public static OperationType fromCode(Integer code) {
        if (code == null) return null;
        return Arrays.stream(OperationType.values())
                .filter(type -> type.code == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown operationType code: " + code));
    }

    public static OperationType fromLabel(String label) {
        if (label == null || label.isEmpty()) return null;
        return Arrays.stream(OperationType.values())
                .filter(type -> type.label == label)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown operationType label: " + label));
    }
}
