package com.example.mj_uc.broadcast.data.remote;

import com.example.mj_uc.broadcast.data.model.DetailsCall;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {

    @Headers("Content-Type: application/json")
    @POST("/broadcastWebService/detail")
    Call<Void> saveDetailCall(@Body DetailsCall detailsCall);
}
