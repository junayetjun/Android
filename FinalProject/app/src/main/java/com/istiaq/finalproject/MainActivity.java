package com.istiaq.finalproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.activity.EdgeToEdge;

import com.istiaq.finalproject.model.GeocodingResponse;
import com.istiaq.finalproject.model.MeteoResponse;
import com.istiaq.finalproject.service.OpenMeteoApi;
import com.istiaq.finalproject.service.OpenMeteoGeocodingApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText cityEditText;
    private Button checkWeatherButton;
    private TextView weatherResultTextView;

    private OpenMeteoGeocodingApi geocodingApi;
    private OpenMeteoApi weatherApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cityEditText = findViewById(R.id.cityEditText);
        checkWeatherButton = findViewById(R.id.checkWeatherButton);
        weatherResultTextView = findViewById(R.id.weatherResultTextView);

        // Retrofit for Geocoding API (to convert city name to lat/lon)
        Retrofit geoRetrofit = new Retrofit.Builder()
                .baseUrl("https://geocoding-api.open-meteo.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        geocodingApi = geoRetrofit.create(OpenMeteoGeocodingApi.class);

        // Retrofit for Weather API
        Retrofit weatherRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.open-meteo.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        weatherApi = weatherRetrofit.create(OpenMeteoApi.class);

        checkWeatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = cityEditText.getText().toString().trim();
                if (city.isEmpty()) {
                    weatherResultTextView.setText("Please enter a city name.");
                    return;
                }
                fetchCoordinatesAndWeather(city);
            }
        });
    }

    private void fetchCoordinatesAndWeather(String cityName) {
        geocodingApi.getLocation(cityName, 1).enqueue(new Callback<GeocodingResponse>() {
            @Override
            public void onResponse(Call<GeocodingResponse> call, Response<GeocodingResponse> response) {
                if (!response.isSuccessful() || response.body() == null
                        || response.body().results == null || response.body().results.isEmpty()) {
                    weatherResultTextView.setText("City not found.");
                    return;
                }

                GeocodingResponse.Location location = response.body().results.get(0);
                double lat = location.latitude;
                double lon = location.longitude;

                fetchWeather(lat, lon, location.name);
            }

            @Override
            public void onFailure(Call<GeocodingResponse> call, Throwable t) {
                weatherResultTextView.setText("Error fetching location: " + t.getMessage());
            }
        });
    }

    private void fetchWeather(double lat, double lon, String cityName) {
        weatherApi.getWeather(lat, lon, true).enqueue(new Callback<MeteoResponse>() {
            @Override
            public void onResponse(Call<MeteoResponse> call, Response<MeteoResponse> response) {
                if (!response.isSuccessful() || response.body() == null || response.body().current_weather == null) {
                    weatherResultTextView.setText("Failed to get weather info.");
                    return;
                }

                MeteoResponse.CurrentWeather weather = response.body().current_weather;
                String result = String.format("City: %s\nTemperature: %.1f°C\nWind Speed: %.1f km/h\nWind Direction: %.0f°\nTime: %s",
                        cityName, weather.temperature, weather.windspeed, weather.winddirection, weather.time);

                weatherResultTextView.setText(result);
            }

            @Override
            public void onFailure(Call<MeteoResponse> call, Throwable t) {
                weatherResultTextView.setText("Error fetching weather: " + t.getMessage());
            }
        });
    }
}
