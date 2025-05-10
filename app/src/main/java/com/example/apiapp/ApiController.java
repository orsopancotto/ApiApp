package com.example.apiapp;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiController {
    //https://api.open-meteo.com/v1/forecast?latitude=44.4938&longitude=11.3387&current=temperature_2m,weather_code,precipitation,rain,showers,snowfall,apparent_temperature,is_day,wind_speed_10m,wind_direction_10m,wind_gusts_10m,relative_humidity_2m&timezone=auto
    @GET("forecast")
    Call<WeatherModel> getWeatherData(@QueryMap Map<String, String> query);
    @GET("forecast")
    Call<ResponseBody> getRawResponse(@QueryMap Map<String, String> query);
}
