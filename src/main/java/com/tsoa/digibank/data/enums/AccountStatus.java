package com.tsoa.digibank.data.enums;

import lombok.Getter;

@Getter
public enum AccountStatus {
    CREATED(1, "created"),
    ACTIVATED(2, "activated"),
    SUSPENDED(3, "suspended");

    private final int code;
    private final String label;

    AccountStatus(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public static AccountStatus fromCode(Integer code) {
        if (code == null) return null;
        return switch (code) {
            case 1 -> CREATED;
            case 2 -> ACTIVATED;
            case 3 -> SUSPENDED;
            default -> throw new IllegalArgumentException("Unknown accountStatus code: " + code);
        };
    }
}
