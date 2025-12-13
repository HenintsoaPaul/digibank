package com.tsoa.digibank.responses;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class ApiOkResponse<T> extends ApiResponse<T> {
    public ApiOkResponse(String message, T data) {
        super(
                HttpStatus.OK.value(),
                message,
                data
        );
    }
}
