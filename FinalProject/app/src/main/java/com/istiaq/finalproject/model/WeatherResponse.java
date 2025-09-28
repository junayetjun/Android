package com.istiaq.finalproject.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponse {

    @SerializedName("name")
    public String cityName;

    @SerializedName("weather")
    public List<Weather> weatherList;

    @SerializedName("main")
    public Main main;

    public static class Weather {
        @SerializedName("description")
        public String description;
    }

    public static class Main {
        @SerializedName("temp")
        public float temp;
    }
}
