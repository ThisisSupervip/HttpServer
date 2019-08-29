package com.thisisvip.enums;

import java.util.HashMap;
import java.util.Map;

public enum ContentType {

    JS("application/javascript","js"),
    CSS("text/css","css"),
    HTML("text/html","html"),
    TXT("text/plain","txt"),
    JPG("image/jpeg","jpg");

    public static Map<String,ContentType> suffixAndType = new HashMap<>();
    static {
        for(ContentType type:ContentType.values()){
            suffixAndType.put(type.getExtension(),type);
        }
    }

    private String contentType;

    private String extension;

    public String getContentType() {
        return contentType;
    }

    public String getExtension() {
        return extension;
    }

    private ContentType(String contentType,String extension){
        this.contentType = contentType;
        this.extension = extension;
    }


    @Override
    public String toString(){
        return extension;
    }
}
