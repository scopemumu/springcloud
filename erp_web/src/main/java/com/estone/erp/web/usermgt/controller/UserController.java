package com.estone.erp.web.usermgt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.estone.erp.common.module.usermgt.client.IUserServiceClient;

@Controller
public class UserController {

    @Autowired
    IUserServiceClient userServiceClient;

    @GetMapping(value = "/user/detail")
    public String getById() {
        return "usermgt/user";
    }
}
