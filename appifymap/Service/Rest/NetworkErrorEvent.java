package com.djcanadastudio.appifymap.Service.Rest;

import java.io.IOException;

import okhttp3.HttpUrl;

/**
 * Created by desenguo on 2016-08-29.
 */
public class NetworkErrorEvent {
    private String name;
    private HttpUrl url;

    public NetworkErrorEvent(IOException error, HttpUrl url) {
        name = error.getClass().getCanonicalName();
        this.url = url;
    }

    public HttpUrl getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }
}
