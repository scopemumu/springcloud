package com.estone.erp.common.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class TokenData implements Serializable{
    private static final long serialVersionUID = 9163738221440903222L;

    private String accessToken;
    
    private String ip;

    private String userAgent;
}
