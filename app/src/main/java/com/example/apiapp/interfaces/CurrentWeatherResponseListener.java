package com.example.apiapp.interfaces;

import com.example.apiapp.CWDataBoundView;
import com.example.apiapp.WeatherModel;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public interface CurrentWeatherResponseListener extends BaseResponseListener{
    ArrayList<CWDataBoundView> currentWeatherViews = new ArrayList<>();

    void onResponse(@Nullable WeatherModel.Current responseBody);
}
