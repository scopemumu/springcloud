package com.estone.erp.common.model;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ErrorCode {
    public ErrorCode(HttpStatus httpStatus) {
        this.value = httpStatus.value();
        this.reasonPhrase = httpStatus.getReasonPhrase();
    }

    public ErrorCode(ServiceStatus serviceStatus) {
        this.value = serviceStatus.getValue();
        this.reasonPhrase = serviceStatus.getReasonPhrase();
    }

    private final int value;

    private final String reasonPhrase;
}
