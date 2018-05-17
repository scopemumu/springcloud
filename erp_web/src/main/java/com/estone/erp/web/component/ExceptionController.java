package com.estone.erp.web.component;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.estone.erp.common.exception.BusinessException;
import com.estone.erp.common.model.ApiResult;
import com.estone.erp.common.model.ErrorCode;
import com.estone.erp.common.security.properties.SecurityProperties;
import com.estone.erp.common.util.WebUtils;

/**
 * 重写BasicErrorController,主要负责系统的异常页面的处理以及错误信息的显示
 * 
 * @see org.springframework.boot.autoconfigure.web.BasicErrorController
 * @see org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration
 *
 * @author Kevin
 */
@Controller
@RequestMapping(value = "error")
@EnableConfigurationProperties({ ServerProperties.class })
public class ExceptionController implements ErrorController {
    private ErrorAttributes errorAttributes;

    @Autowired
    private ServerProperties serverProperties;

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 初始化ExceptionController
     */
    @Autowired
    public ExceptionController(ErrorAttributes errorAttributes) {
        Assert.notNull(errorAttributes, "ErrorAttributes must not be null");
        this.errorAttributes = errorAttributes;
    }

    /**
     * 定义404的ModelAndView
     */
    @RequestMapping(produces = "text/html", value = "404")
    public ModelAndView errorHtml404(HttpServletRequest request, HttpServletResponse response) {
        ApiResult<?> apiResult = getApiResult(request);
        Map<String, Object> result = new HashMap<>(1);
        result.put("apiResult", apiResult);
        return new ModelAndView("common/404", result);
    }

    /**
     * 定义500的ModelAndView
     */
    @RequestMapping(produces = "text/html", value = "500")
    public ModelAndView errorHtml500(HttpServletRequest request, HttpServletResponse response) {
        ApiResult<?> apiResult = getApiResult(request);
        Map<String, Object> result = new HashMap<>(1);
        result.put("apiResult", apiResult);
        return new ModelAndView("common/500", result);
    }

    /**
     * 定义404, 500的JSON数据
     */
    @RequestMapping(value = { "404", "500" })
    @ResponseBody
    public ApiResult<?> error404(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        return getApiResult(request);
    }

    /**
     * Determine if the stacktrace attribute should be included.
     * 
     * @param request the source request
     * @param produces the media type produced (or {@code MediaType.ALL})
     * @return if the stacktrace attribute should be included
     */
    protected boolean isIncludeStackTrace(HttpServletRequest request, MediaType produces) {
        ErrorProperties.IncludeStacktrace include = this.serverProperties.getError().getIncludeStacktrace();
        if (include == ErrorProperties.IncludeStacktrace.ALWAYS) {
            return true;
        }
        if (include == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM) {
            return getTraceParameter(request);
        }
        return false;
    }

    /**
     * 获取错误的信息
     */
    public Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        return this.errorAttributes.getErrorAttributes(new ServletRequestAttributes(request), includeStackTrace);
    }

    private ApiResult<?> getApiResult(HttpServletRequest request) {
        Throwable e = errorAttributes.getError(new ServletRequestAttributes(request));
        errorAttributes.getErrorAttributes(new ServletRequestAttributes(request), true);
        return ApiResult.newError(new BusinessException(securityProperties.getZuulProxyName(),
                new ErrorCode(WebUtils.getHttpStatus(request)), e));
    }

    /**
     * 是否包含trace
     */
    private boolean getTraceParameter(HttpServletRequest request) {
        String parameter = request.getParameter("trace");
        if (parameter == null) {
            return false;
        }
        return !"false".equals(parameter.toLowerCase());
    }

    /**
     * 实现错误路径,暂时无用
     * 
     * @see ExceptionMvcAutoConfiguration#containerCustomizer()
     * @return
     */
    @Override
    public String getErrorPath() {
        return "";
    }
}
