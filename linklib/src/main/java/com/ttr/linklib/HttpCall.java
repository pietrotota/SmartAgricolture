package com.ttr.linklib;

import java.util.HashMap;

/**
 * Created by vitodtagliente on 09/11/16.
 */

public class HttpCall {

    public static final int GET = 1;
    public static final int POST = 2;

    private String url;
    private int methodtype;
    private HashMap<String,String> params;

    public HttpCall(String url, int method) {
        this.url = url;
        this.methodtype = method;
    }

    public HttpCall(String url) {
        this.url = url;
        this.methodtype = GET;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getMethodtype() {
        return methodtype;
    }

    public void setMethodtype(int methodtype) {
        this.methodtype = methodtype;
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }
}