package com.estone.erp.common.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.estone.erp.common.model.ApiResult;
import com.estone.erp.common.model.ErrorCode;
import com.estone.erp.common.security.properties.SecurityProperties;
import com.estone.erp.common.util.WebUtils;

@RestControllerAdvice(basePackages = { "com.estone.erp" })
public class RestExceptionHandler {
    @Autowired
    private SecurityProperties securityProperties;

    @ExceptionHandler
    @ResponseStatus
    public ApiResult<?> runtimeExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        BusinessException be = null;
        if (e instanceof BusinessException) {
            be = (BusinessException) e;
        }
        else {
            be = new BusinessException(securityProperties.getZuulProxyName(),
                    new ErrorCode(WebUtils.getHttpStatus(request)), e, null);
        }
        ApiResult<?> apiResult = ApiResult.newError(be);

        return apiResult;
    }
}
