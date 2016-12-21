package com.example.sathapornsunthornpan.onlinebikercommunity.service;


import com.example.sathapornsunthornpan.onlinebikercommunity.model.Blog2;
import com.example.sathapornsunthornpan.onlinebikercommunity.model.ListBlog;
import com.example.sathapornsunthornpan.onlinebikercommunity.model.MyResponse;
import com.example.sathapornsunthornpan.onlinebikercommunity.user.models.ServerResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BlogAPIService {


//    @FormUrlEncoded
    @POST("blog/insert")
    Call<MyResponse> insertBlog(@Body Blog2 blog);

    @GET("blog/all")
    Call<List<ListBlog>> getBlogList();

}


