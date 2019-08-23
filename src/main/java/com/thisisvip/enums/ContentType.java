package com.thisisvip.enums;

public enum ContentType {

    JSON("application/json"),
    JS("application/javascript"),
    CSS("text/css"),
    HTML("text/html"),
    TXT("text/plain"),
    XML("text/xml"),
    JPG("image/jpeg"),
    GIF("image/gif"),
    PNG("image/png");

    private String contentType;

    public String getContentType() {
        return contentType;
    }

    private ContentType(String contentType){
        this.contentType = contentType;
    }
}
