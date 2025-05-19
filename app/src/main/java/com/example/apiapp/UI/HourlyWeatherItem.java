package com.example.apiapp.UI;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.apiapp.R;
import com.example.apiapp.WeatherModel;

import org.jetbrains.annotations.NotNull;

public class HourlyWeatherItem extends DataBoundView {
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
    protected int setViewResource() {
        return R.layout.hourly_weather_item;
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
    public void bindData(@Nullable WeatherModel data, @Nullable String indexValue) {
        if(data == null){
            displayDebugMessage("No info");
            return;
        }

        int index = data.hourly.time.indexOf(indexValue);
        if(index == -1 || indexValue == null || indexValue.isEmpty()){
            displayDebugMessage("Err");
            return;
        }

        time = indexValue.substring(indexValue.length() - 5);
        temperature_2m = data.hourly.temperature_2m.get(index);
        apparent_temperature = data.hourly.apparent_temperature.get(index);
        precipitation_probability = data.hourly.precipitation_probability.get(index);
        weather_code = data.hourly.weather_code.get(index);
        is_day = data.hourly.is_day.get(index);
        displayData();
    }

    private void displayDebugMessage(String message){
        hour.setText(message);
    }
}
