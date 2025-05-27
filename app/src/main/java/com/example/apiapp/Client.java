package com.example.apiapp;

import com.example.apiapp.interfaces.ApiController;
import com.example.apiapp.interfaces.CurrentWeatherResponseListener;
import com.example.apiapp.interfaces.BaseResponseListener;
import com.example.apiapp.interfaces.HourlyWeatherResponseListener;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class Client {
    private static final String baseUrl = "https://api.open-meteo.com/v1/";
    private static ApiController controller = null;
    private static ArrayList<Call<WeatherModel>> currentWeatherCalls = null;

    private Client(){
        throw new AssertionError("Static!");
    }

    public static void start(){
        if(controller != null || currentWeatherCalls != null) return;

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        controller = retrofit.create(ApiController.class);
        currentWeatherCalls = new ArrayList<>();
    }

    public ApiController getController(){
        return controller;
    }

    public String getBaseUrl(){
        return baseUrl;
    }

    public static void getCurrentWeather(CurrentWeatherResponseListener listener){
        QueryBuilder queryBuilder = new QueryBuilder
                .Builder(44.4938f, 11.3387f)
                .setTimezone("CET")
                .setAllCurrentQueryParams()
                .build();

        Call<WeatherModel> call = controller.getWeatherData(queryBuilder.createQuery());

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response){
                if(response.code() != 200){
                    listener.handleResponseCode(response.code());
                    currentWeatherCalls.remove(call);
                }

                listener.onResponse(response.body().current);
                currentWeatherCalls.remove(call);
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable throwable) {
                listener.handleResponseCode(throwable.getMessage());
                listener.onResponse(null);
                currentWeatherCalls.remove(call);
            }
        });

        currentWeatherCalls.add(call);
    }

    public static void getHourlyWeather(HourlyWeatherResponseListener listener){
        QueryBuilder queryBuilder = new QueryBuilder
                .Builder(44.4938f, 11.3387f)
                .setTimezone("CET")
                .setAllHourlyQueryParams()
                .build();

        Call<WeatherModel> call = controller.getWeatherData(queryBuilder.createQuery());

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response){
                if(response.code() != 200){
                    listener.handleResponseCode(response.code());
                    currentWeatherCalls.remove(call);
                }
                else if(response.body() == null){
                    listener.handleResponseCode(response.code());
                    currentWeatherCalls.remove(call);
                }

                listener.onResponse(response.body().hourly);
                currentWeatherCalls.remove(call);
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable throwable) {
                listener.handleResponseCode(throwable.getMessage());
                listener.onResponse(null);
                currentWeatherCalls.remove(call);
            }
        });

        currentWeatherCalls.add(call);
    }

    public static void getRawJson(BaseResponseListener listener){
        QueryBuilder queryBuilder = new QueryBuilder
                .Builder(44.4938f, 11.3387f)
                .setTimezone("CET")
                .setAllCurrentQueryParams()
                .build();

        Call<ResponseBody> call = controller.getRawResponse(queryBuilder.createQuery());

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response){
                if(response.code() != 200){
                    listener.handleResponseCode(response.code());
                    currentWeatherCalls.remove(call);
                }

                listener.onRawJsonResponse(response.body().toString());
                currentWeatherCalls.remove(call);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                listener.handleResponseCode(throwable.getMessage());
                listener.onRawJsonResponse("Err");
                currentWeatherCalls.remove(call);
            }
        });
    }

    public static void cancelWebRequests(){
        if(currentWeatherCalls.isEmpty()) return;

        for(var call : currentWeatherCalls){
            call.cancel();
        }
    }
}