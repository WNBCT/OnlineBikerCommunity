package com.example.sathapornsunthornpan.onlinebikercommunity.config;


import android.support.annotation.NonNull;

import okhttp3.HttpUrl;

public class Constants {

    public static final String SCHEMA = "http";
//    public static final String HOST = "10.0.2.2";
    public static final String HOST = "172.19.196.190";
    public static final int    PORT = 80;

    public static final String URI = "project/mobileonlinebiker/";

    public static final String FEED_NEWS_OPERATION = "rss/news";
    public static final String REGISTER_OPERATION = "register";
    public static final String LOGIN_OPERATION = "login";
    public static final String CHANGE_PASSWORD_OPERATION = "chgPass";

    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";
    public static final String IS_LOGGED_IN = "isLoggedIn";

    public static final String USER_ID = "user_id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String UNIQUE_ID = "unique_id";

    public static final String TAG = "Online Biker";

    @NonNull
    public static HttpUrl URL() {
        return new HttpUrl.Builder()
                .scheme(SCHEMA)
                .host(HOST).port(PORT)
                .addPathSegments(URI)
                .build();
    }

    public static HttpUrl RootURL(String s) {
        return new HttpUrl.Builder()
                .scheme(SCHEMA)
                .host(HOST).port(PORT)
                .addPathSegments(s)
                .build();
    }

    public static HttpUrl URL(String s) {
        return new HttpUrl.Builder()
                .scheme(SCHEMA)
                .host(HOST).port(PORT)
                .addPathSegments(URI + s)
                .build();
    }
}
