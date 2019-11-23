package com.example.homeservicestask.server;

import com.example.homeservicestask.models.ServicesModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceInterface {

    @GET("servicesLocate/{latitude}/{longitude}/{RADIUS}")
    public Call<ServicesModel> getData(@Path("latitude") Double latitude, @Path("longitude") Double longitude, @Path("RADIUS") int radius
            , @Query("lang") String lang);

    //  https://ezhalha.com.sa/api/v2/servicesLocate/26.464103/50.075097/RADIUS=10KM?lang=ar

    @GET("servicesLocate/26.464103/50.075097/RADIUS=10KM?lang=ar")
    public Call<ServicesModel> getData();
}
