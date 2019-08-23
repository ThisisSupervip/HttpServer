package com.thisisvip.enums;

public enum Method {

    DELETE("DElETE"),
    GET("GET"),
    HEAD("HEAD"),
    POST("POST"),
    PUT("PUT"),
    OPTIONS("OPTIONS"),
    TRACE("TRACE"),
    CONNECT("CONNECT");

    private String method;

    public String getMethod() {
        return method;
    }

    private Method(String method) {
        this.method = method;
    }
}
