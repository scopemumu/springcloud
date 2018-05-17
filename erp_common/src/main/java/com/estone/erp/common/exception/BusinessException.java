package com.estone.erp.common.exception;

import com.estone.erp.common.model.ErrorCode;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = -4033171327412143319L;

    private String service;

    private ErrorCode errorCode;

    private Object[] args;

    private String clazz;

    public BusinessException(String service, ErrorCode errorCode, Throwable cause) {
        super(cause);
        this.service = service;
        this.errorCode = errorCode;
        if (cause != null) {
            this.clazz = cause.getClass().getName();
        }
    }

    public BusinessException(String service, ErrorCode errorCode, Throwable cause, Object[] args) {
        this(service, errorCode, cause);
        this.args = args;
    }
}
