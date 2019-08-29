package com.thisisvip;

import com.thisisvip.enums.Method;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpRequest {

    private Method method;
    private String requestURI;
    private String version;

    public HttpRequest(InputStream inputStream) {

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder request = new StringBuilder();
        while (true) {
            String str = null;
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (str.equals("")) {
                break;
            }
            request.append(str + "\n");
        }
        parseRequest(request.toString());

    }

    private void parseRequest(String req) {
        String[] split = req.split("\n|\r");
        String requestLine = split[0];
        String[] split1 = requestLine.split("\\s+");
        try {
            method = Method.valueOf(split1[0]);
        } catch (Exception e) {
            method = Method.UNDEFINED;
        }
        requestURI = split1[1];
        version = split1[2];
    }

    public Method getMethod() {
        return method;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public String getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return String.format("Method: %s\nRequestURI: %s\nversion: %s\n", method, requestURI, version);
    }
}
