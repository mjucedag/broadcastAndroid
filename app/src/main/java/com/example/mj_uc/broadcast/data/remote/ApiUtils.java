package com.example.mj_uc.broadcast.data.remote;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "http://10.0.2.2/";

    public static APIService getAPIService() {

        return RetrofitClient.getRetrofit(BASE_URL).create(APIService.class);
    }
}
