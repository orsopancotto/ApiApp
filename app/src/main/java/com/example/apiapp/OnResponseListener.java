package com.example.apiapp;

import androidx.annotation.Nullable;

public interface OnResponseListener {
    public void onWeatherDataResponse(@Nullable WeatherModel responseBody);
    public void onRawJsonResponse(String json);
    public void handleResponseCode(int code, String errorMessage);
}