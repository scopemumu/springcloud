package com.estone.erp.usermgt.security.userdetail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.estone.erp.usermgt.base.service.IUserService;

/**
 * 登录是自定义判断用户名密码是否正确 从数据库查询进行对比判断
 */
@Configuration
public class DomainUserDetailsService implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.estone.erp.usermgt.base.model.User user = userService.getByUserName(username);
        if (null == user) {
            logger.warn("用户" + username + "不存在");
        }
        logger.warn("表单登录用户名:" + username);
        UserDetails userDetail = new User(username, passwordEncoder().encode(user.getPassword()), true, true, true,
                true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER,ROLE_wo,hehe"));
        return userDetail;
    }

}
