package com.hopware.unirestprueba.domain;

import java.util.Map;

/**
 * Created by gerardoparajeles on 3/5/17.
 */
public class RequestDTO {

    private String url;
    private String type;
    private Map<String,String> headers;
    private String body;

    public RequestDTO() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUrl() {

        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
