package com.example.apiapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiController {
    @GET("forecast?latitude=44.4938&longitude=11.3387&timezone=CET&hourly=temperature_2m&hourly=apparent_temperature")
    Call<WeatherData> getWeatherData();

}
