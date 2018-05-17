package com.estone.erp.common.security.rbac.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.estone.erp.common.module.usermgt.client.IUserServiceClient;
import com.estone.erp.common.security.rbac.IRbacService;

/**
 * 权限判断类, 根据请求和数据库库查询的权限对比, 判断是否有权限访问
 */
@Component("iRbacService")
public class RbacServiceImpl implements IRbacService {
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    private IUserServiceClient iUserService;

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            List<String> permissions = iUserService.findPermissionsByUsername(username);
            for (String url : permissions) {
                if (antPathMatcher.match(url, request.getRequestURI())) {
                    hasPermission = true;
                    break;
                }
            }
        }
        else if (!"anonymousUser".equals(principal)) {
            List<String> permissions = iUserService.findPermissionsByUsername(principal.toString());
            for (String url : permissions) {
                if (antPathMatcher.match(url, request.getRequestURI())) {
                    hasPermission = true;
                    break;
                }
            }
        }
        return hasPermission;
    }

}
