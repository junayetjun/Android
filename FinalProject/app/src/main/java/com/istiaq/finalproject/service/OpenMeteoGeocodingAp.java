package com.istiaq.finalproject.service;

import com.istiaq.finalproject.model.GeocodingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenMeteoGeocodingApi {
    @GET("v1/search")
    Call<GeocodingResponse> getLocation(
            @Query("name") String cityName,
            @Query("count") int count
    );
}
