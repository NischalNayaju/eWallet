package com.nce.project.gojiiv1.helper;

import com.google.gson.annotations.SerializedName;

public class DefaultResponse {
    @SerializedName("code")
    private  boolean code;

    @SerializedName("message")
    private String msg;

    public DefaultResponse(boolean code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public boolean iscode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}