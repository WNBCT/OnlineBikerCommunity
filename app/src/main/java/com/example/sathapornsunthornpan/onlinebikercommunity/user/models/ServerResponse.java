package com.example.sathapornsunthornpan.onlinebikercommunity.user.models;


public class ServerResponse {

    private String success;
    private String error;

    private String result;
    private String message;
    private User user;

    public String getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }


    public String getSuccess() {
        return success;
    }

    public String getError() {
        return error;
    }
}