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
import com.example.apiapp.enums.WeatherCode;

public class CurrentGeneralWeatherView extends CWDataBoundView {

    TextView tvGeneralInfos;
    private float temperature_2m;
    private int weather_code;
    private float apparent_temperature;
    private int is_day;

    public CurrentGeneralWeatherView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getViewResource() {
        return R.layout.view_current_general_infos;
    }

    @Override
    protected void setWidgetsReferences() {
        tvGeneralInfos = findViewById(R.id.tvCurrentGeneralInfos);
    }

    @Override
    protected void displayData() {
        StringBuilder s = new StringBuilder();

        s.append("Weather: ").append(WeatherCode.getCode(weather_code).toString()).append("\n");
        s.append("Temperature: ").append(temperature_2m).append("°C\n");
        s.append("Apparent temperature: ").append(apparent_temperature).append("°C\n");
        s.append(is_day == 1 ? "Daylight" : "Night");

        tvGeneralInfos.setText(s.toString());
    }

    @Override
    public void bindData(@Nullable WeatherModel.Current data) {
        if(data == null){
            displayDebugMessage();
            return;
        }

        temperature_2m = data.temperature_2m;
        weather_code = data.weather_code;
        apparent_temperature = data.apparent_temperature;
        is_day = data.is_day;

        displayData();
    }

    private void displayDebugMessage(){
        String s = "No info :(";
        tvGeneralInfos.setText(s);
    }
}
