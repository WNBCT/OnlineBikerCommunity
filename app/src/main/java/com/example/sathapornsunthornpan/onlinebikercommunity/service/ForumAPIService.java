package com.example.sathapornsunthornpan.onlinebikercommunity.service;

import com.example.sathapornsunthornpan.onlinebikercommunity.model.Blog2;
import com.example.sathapornsunthornpan.onlinebikercommunity.model.MyResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;



public interface ForumAPIService {

    @FormUrlEncoded
    @POST("forum/create")
    Call<MyResponse> insertForum(@Field("user_id") String user_id,
                                 @Field("title") String forum_topic,
                                 @Field("details") String forum_detail);

}
