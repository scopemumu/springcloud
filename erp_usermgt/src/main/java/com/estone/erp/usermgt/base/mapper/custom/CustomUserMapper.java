package com.estone.erp.usermgt.base.mapper.custom;

import java.util.List;

public interface CustomUserMapper {
    List<String> findPermissionsByUsername(String username);
}