package com.tiekoura.learningmicroservices.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private int code;
    public ErrorResponse(String message, int code) {
        super();
        this.message = message;
    }
}
