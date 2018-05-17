package com.estone.erp.web.component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.entity.ContentType;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.estone.erp.common.exception.BusinessException;
import com.estone.erp.common.model.ApiResult;
import com.estone.erp.common.model.ErrorCode;
import com.estone.erp.common.security.properties.SecurityProperties;
import com.estone.erp.common.util.SpringUtils;
import com.estone.erp.common.util.WebUtils;

import lombok.extern.slf4j.Slf4j;

/***
 * 统一Contronller异常处理
 * 
 * @author Kevin
 *
 */
@Slf4j
@Order(1)
@Component
public class WebExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        log.error(ex.getMessage(), ex);
        BusinessException be = null;
        if (ex instanceof BusinessException) {
            be = (BusinessException) ex;
        }
        else {
            be = new BusinessException(SpringUtils.getBean(SecurityProperties.class).getZuulProxyName(),
                    new ErrorCode(WebUtils.getHttpStatus(request)), ex, null);
        }

        ApiResult<?> apiResult = ApiResult.newError(be);
        if (WebUtils.isAjaxRequest(request)) {
            try {
                response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
                response.getWriter().write(JSON.toJSONString(apiResult));
            }
            catch (IOException e) {
                log.error(ex.getMessage(), ex);
            }

            return null;
        }
        else {
            response.setStatus(HttpServletResponse.SC_OK);
            Map<String, Object> result = new HashMap<>(1);
            result.put("apiResult", apiResult);

            return new ModelAndView("common/500", result);
        }
    }
}
