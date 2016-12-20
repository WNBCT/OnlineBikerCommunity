package com.example.sathapornsunthornpan.onlinebikercommunity.user.service;

import com.example.sathapornsunthornpan.onlinebikercommunity.user.models.ServerResponse;
import com.example.sathapornsunthornpan.onlinebikercommunity.user.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RequestInterface {



    @POST("user/register")
    Call<ServerResponse> registerUser(@Body User user);


    @POST("user/login")
    Call<ServerResponse> loginUser(@Body User user);


    @POST("user/chgPass")
    Call<ServerResponse> changePasswordUser(@Body User user);

}
