package com.istiaq.finalproject.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GeocodingResponse {
    @SerializedName("results")
    public List<com.istiaq.finalproject.service.GeocodingResponse.Location> results;

    public static class Location {
        @SerializedName("name")
        public String name;

        @SerializedName("latitude")
        public double latitude;

        @SerializedName("longitude")
        public double longitude;
    }
}