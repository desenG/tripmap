package com.djcanadastudio.appifymap.Service.Rest;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by desenguo on 2016-08-29.
 */
public class HTTPErrorEvent {
    private int code;
    private String message;
    private String url;
    private String body;

    public HTTPErrorEvent(Response response) {
        code = response.code();
        message = response.message();
        url = response.request().url().toString();
        try {
            body = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return url;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public String getBody() {
        return body;
    }
}
