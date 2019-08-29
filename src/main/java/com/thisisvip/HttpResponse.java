package com.thisisvip;

import com.thisisvip.enums.ContentType;
import com.thisisvip.enums.Status;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class HttpResponse {

    public static final String HTTP_VERSION = "HTTP/1.0";


    String statusLine;

    ResponseHeader responseHeader;

    byte[] entity;

    public HttpResponse(HttpRequest request) {

        switch (request.getMethod()) {
            case HEAD:
                responseHeader = new ResponseHeader(Status.OK);
                break;
            case GET:
                File file = new File("D://test" + request.getRequestURI());
                ContentType contentType = null;
                if ((file.isDirectory()&&!(file=new File(file.getPath()+"index.html")).exists())) {
                    break;
                }
                if (file.exists()) {
                    String fileName = file.getName();
                    String suffix = fileName.substring(fileName.lastIndexOf('.')+1);
                    try {
                        entity = setEntity(file);
                    } catch (IOException e){
                        responseHeader = new ResponseHeader(Status.NOT_FOUND);
                    }
                    responseHeader = new ResponseHeader(Status.OK,ContentType.suffixAndType.get(suffix));
                } else {
                    responseHeader = new ResponseHeader(Status.NOT_FOUND);
                }
                break;
            default:


        }
    }

    private byte[] setEntity(File file) throws IOException {
        long length = file.length();
        byte[] res = new byte[(int)length];
        FileInputStream fileInputStream = new FileInputStream(file);
        fileInputStream.read(res);
        return res;
    }

    private static class ResponseHeader {

        private Status status;

        private ContentType contentType;

        private String date;

        ResponseHeader(Status status) {
            this.status = status;
            DateFormat dateFormat = new SimpleDateFormat("EEE,d MMM yyyy hh:mm:ss Z", Locale.ENGLISH);
            date = dateFormat.format(new Date());
        }

        ResponseHeader(Status status, ContentType contentType) {
            this(status);
            this.contentType = contentType;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(HTTP_VERSION).append(' ').append(status.getStatusCode()).append(' ').append(status.getDescription()).append("\r\n");
            sb.append("Content-Type: ").append(contentType != null ? contentType.getContentType() : ContentType.HTML).append("\r\n");
            sb.append("Date: ").append(date).append("\r\n");
            sb.append("\r\n");
            return sb.toString();
        }

    }

    public byte[] toBytes() {

        byte[] header = responseHeader.toString().getBytes();
        int length = header.length;
        if(entity!=null){
           length += entity.length;
        }
        byte[] res = new byte[length];
        System.arraycopy(header,0,res,0,header.length);
        if(length>header.length){
            System.arraycopy(entity,0,res,header.length,entity.length);
        }
        return res;
    }

}
