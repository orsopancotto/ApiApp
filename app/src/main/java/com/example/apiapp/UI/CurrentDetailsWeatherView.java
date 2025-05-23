package com.example.apiapp.UI;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.apiapp.CWDataBoundView;
import com.example.apiapp.DataBoundView;
import com.example.apiapp.R;
import com.example.apiapp.WeatherModel;

public class CurrentDetailsWeatherView extends CWDataBoundView {
    TextView tvCurrentDetails;
    private float rain;
    private float showers;
    private float snowfall;
    private float wind_speed_10m;
    private int wind_direction_10m;
    private float wind_gusts_10m;
    private int relative_humidity_2m;

    public CurrentDetailsWeatherView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getViewResource() {
        return R.layout.view_current_info_details;
    }

    @Override
    protected void setWidgetsReferences() {
        tvCurrentDetails = findViewById(R.id.tvCurrentInfoDetails);
    }

    @Override
    protected void displayData() {
        StringBuilder s = new StringBuilder();
        s.append("Rain: ").append(rain).append("mm\n");
        s.append("Showers: ").append(showers).append("mm\n");
        s.append("Snowfall: ").append(snowfall).append("cm\n");
        s.append("Wind speed: ").append(wind_speed_10m).append("km/h\n");
        s.append("Wind direction: ").append(wind_direction_10m ).append("°\n");
        s.append("Wind gusts: ").append(wind_gusts_10m).append("km/h\n");
        s.append("Humidity: ").append(relative_humidity_2m).append("%");
        tvCurrentDetails.setText(s);
    }

    @Override
    public void bindData(@Nullable WeatherModel.Current data) {
        if(data == null){
            displayDebugMessage();
            return;
        }

        rain = data.rain;
        showers = data.showers;
        snowfall = data.snowfall;
        wind_speed_10m = data.wind_speed_10m;
        wind_direction_10m = data.wind_direction_10m;
        wind_gusts_10m = data.wind_gusts_10m;
        relative_humidity_2m = data.relative_humidity_2m;

        displayData();
    }

    private void displayDebugMessage(){
        String s = "No info";
        tvCurrentDetails.setText(s);
    }
}
