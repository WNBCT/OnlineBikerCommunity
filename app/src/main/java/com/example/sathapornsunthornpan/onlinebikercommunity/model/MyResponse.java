package com.example.sathapornsunthornpan.onlinebikercommunity.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sathapornsunthornpan on 12/21/16.
 */

public class MyResponse {

    @SerializedName("message")
    @Expose
    private String message;


    @SerializedName("result")
    @Expose
    private String result;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
