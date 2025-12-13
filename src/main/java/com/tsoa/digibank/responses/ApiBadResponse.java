package com.tsoa.digibank.responses;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class ApiBadResponse<T> extends ApiResponse<T> {
    public ApiBadResponse(String message) {
        super(
                HttpStatus.BAD_REQUEST.value(),
                message,
                null
        );
    }

    public ApiBadResponse(String message, T data) {
        super(
                HttpStatus.BAD_REQUEST.value(),
                message,
                data
        );
    }

    public ApiBadResponse(int status, String message, T data) {
        super(
                status,
                message,
                data
        );
    }
}
