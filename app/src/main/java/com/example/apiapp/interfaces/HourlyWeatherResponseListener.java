package com.example.apiapp.interfaces;

import com.example.apiapp.HWDataBoundView;
import com.example.apiapp.WeatherModel;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public interface HourlyWeatherResponseListener extends BaseResponseListener{
    ArrayList<HWDataBoundView> hourlyWeatherViews = new ArrayList<>();

    public void onResponse(@Nullable WeatherModel.Hourly responseBody);
}
