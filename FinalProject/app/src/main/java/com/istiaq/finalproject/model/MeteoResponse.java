package com.istiaq.finalproject.model;

import com.google.gson.annotations.SerializedName;

public class MeteoResponse {
    @SerializedName("current_weather")
    public CurrentWeather current_weather;

    public static class CurrentWeather {
        @SerializedName("temperature")
        public double temperature;

        @SerializedName("windspeed")
        public double windspeed;

        @SerializedName("winddirection")
        public double winddirection;

        @SerializedName("time")
        public String time;
    }
}
