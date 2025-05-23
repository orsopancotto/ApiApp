package com.example.apiapp.UI;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.apiapp.HWDataBoundView;
import com.example.apiapp.R;
import com.example.apiapp.WeatherModel;

import org.jetbrains.annotations.NotNull;

public class HourlyWeatherItem extends HWDataBoundView {
    private TextView hour, temperature, apparentTemp, precipitationProb;
    private String time;
    private float temperature_2m;
    private float apparent_temperature;
    private float precipitation_probability;
    private int weather_code;
    private int is_day;

    public HourlyWeatherItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getViewResource() {
        return R.layout.item_hourly_weather;
    }

    @Override
    protected void setWidgetsReferences() {
        hour = findViewById(R.id.tvHour);
        temperature = findViewById(R.id.tvTemperature);
        apparentTemp = findViewById(R.id.tvApparentTemp);
        precipitationProb = findViewById(R.id.tvPrecipitationProb);
    }

    @Override
    protected void displayData() {
        hour.setText(time);
        temperature.setText(String.format("%s°C", temperature_2m));
        apparentTemp.setText(String.format("%s°C", apparent_temperature));
        precipitationProb.setText(String.format("%s%%", precipitation_probability));
    }

    @Override
    public void bindData(@Nullable WeatherModel.Hourly data) {
        displayDebugMessage("Err");
    }

    @Override
    public void bindData(@Nullable WeatherModel.Hourly data, @NotNull String indexValue) {
        if(data == null){
            displayDebugMessage("No info");
            return;
        }

        int index = data.time.indexOf(indexValue);
        if(index == -1 || indexValue.isEmpty()){
            displayDebugMessage("Err");
            return;
        }

        time = indexValue.substring(indexValue.length() - 5);
        temperature_2m = data.temperature_2m.get(index);
        apparent_temperature = data.apparent_temperature.get(index);
        precipitation_probability = data.precipitation_probability.get(index);
        weather_code = data.weather_code.get(index);
        is_day = data.is_day.get(index);
        displayData();
    }

    private void displayDebugMessage(String message){
        hour.setText(message);
    }
}
