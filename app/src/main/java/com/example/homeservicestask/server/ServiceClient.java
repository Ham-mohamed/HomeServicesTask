package com.example.homeservicestask.server;

import com.example.homeservicestask.models.ServicesModel;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceClient {

    private static final String Base_Url = "https://ezhalha.com.sa/api/v2/";
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public static <S> S cteateService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }


}


