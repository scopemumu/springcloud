package com.estone.erp.common.security.rbac;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

public interface IRbacService {
    
	boolean hasPermission(HttpServletRequest request, Authentication authentication);

}
