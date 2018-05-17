package com.estone.erp.common.model;

public enum ServiceStatus {
    /**
     * rest接口异常，10000-20000
     */
    REST(10000, "rest api occured expcetion"),

    /**
     * 用户管理模块异常，20000-30000
     */
    USER_MGT(20000, ""),

    /**
     * 产品开发模块异常，30000-40000
     */
    PRODUCT_DEV(30000, ""),

    /**
     * web模块异常，40000-50000
     */
    WEB(40000, ""),

    /**
     * web controller异常
     */
    WEB_CONTROLLER(40001, "web controller occured expcetion");

    private ServiceStatus(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    private int value;

    private final String reasonPhrase;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }
}
