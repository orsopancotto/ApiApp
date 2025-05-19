package com.example.apiapp;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiController {
    @GET("forecast")
    Call<WeatherModel> getWeatherData(@QueryMap Map<String, String> query);
    @GET("forecast")
    Call<ResponseBody> getRawResponse(@QueryMap Map<String, String> query);
}