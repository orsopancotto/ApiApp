package com.example.apiapp;

import android.app.Activity;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    private final String baseUrl = "https://api.open-meteo.com/v1/";
    private final ApiController controller;
    private final ArrayList<Call<WeatherModel>> currentWeatherCalls;

    public Client(){
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

    public void getCurrentWeather(ClientActivity activity){
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
                    activity.printQuickMessage("Not ok :(\nCodice: " + response.code(), 0);
                    currentWeatherCalls.remove(call);
                    return;
                }

                activity.displayResponseBody(response.body());
                currentWeatherCalls.remove(call);
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable throwable) {
                activity.printQuickMessage("Cassofica richiesta fallita :(\nMessaggio: " + throwable.getMessage(), 0);
                currentWeatherCalls.remove(call);
            }
        });

        currentWeatherCalls.add(call);
    }

    public void getRawJson(ClientActivity activity){
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
                    activity.printQuickMessage("Not ok :(\nCodice: " + response.code(), 0);
                    currentWeatherCalls.remove(call);
                    return;
                }

                try {
                    activity.displayResponseBody(response.body().string());
                } catch (IOException e) {
                    activity.printQuickMessage("Exception!\n" + e, 1);
                }
                currentWeatherCalls.remove(call);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                activity.printQuickMessage("Cassofica richiesta fallita :(\nMessaggio: " + throwable.getMessage(), 0);
                currentWeatherCalls.remove(call);
            }
        });

        //currentWeatherCalls.add(call);
    }

    public void cancelWebRequests(){
        if(currentWeatherCalls.isEmpty()) return;

        for(var call : currentWeatherCalls){
            call.cancel();
        }
    }
}

