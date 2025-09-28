package com.istiaq.finalproject.service;

import com.istiaq.finalproject.model.MeteoResponse;

import com.istiaq.finalproject.model.MeteoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenMeteoApi {
    @GET("v1/forecast")
    Call<MeteoResponse> getWeather(
            @Query("latitude") double latitude,
            @Query("longitude") double longitude,
            @Query("current_weather") boolean currentWeather
    );
}