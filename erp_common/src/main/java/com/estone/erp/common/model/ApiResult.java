package com.estone.erp.common.model;

import java.io.Serializable;

import com.estone.erp.common.exception.BusinessException;

import lombok.Data;

@Data
public class ApiResult<T> implements Serializable {
    private static final long serialVersionUID = 1271253060972891256L;

    private boolean success = true;

    private String msg;

    private ErrorCode errorCode;

    private String clazz;

    private T result;

    private String redirectUrl;

    public ApiResult() {
        super();
    }

    public static <R> ApiResult<R> newSuccess(R result) {
        ApiResult<R> apiResult = new ApiResult<>();
        apiResult.setResult(result);
        return apiResult;
    }

    public static ApiResult<?> newError(BusinessException exception) {
        ApiResult<?> apiResult = new ApiResult<>();
        apiResult.setSuccess(false);
        apiResult.setMsg(String.format("service: %s, errorCode: %s, msg:  %s.", exception.getService(),
                exception.getErrorCode(), exception.getMessage()));
        apiResult.setErrorCode(exception.getErrorCode());
        apiResult.setClazz(exception.getClazz());

        return apiResult;
    }

    public static ApiResult<?> newError(BusinessException exception, String redirectUrl) {
        ApiResult<?> apiResult = newError(exception);
        apiResult.setRedirectUrl(redirectUrl);

        return apiResult;
    }
}
