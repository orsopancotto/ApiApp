package com.example.apiapp.UI;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.apiapp.HWDataBoundView;
import com.example.apiapp.R;
import com.example.apiapp.WeatherModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.stream.Collectors;

public class HourlyWeatherView extends HWDataBoundView {
    private LinearLayout linearLayout;
    private WeatherModel.Hourly data;

    public HourlyWeatherView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getViewResource() {
        return R.layout.view_hourly_weather;
    }

    @Override
    protected void setWidgetsReferences() {
        linearLayout = findViewById(R.id.llHourlyWeather);
    }

    @Override
    protected void displayData() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH", Locale.getDefault());
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Rome"));
        int startingHour = Integer.parseInt(dateFormat.format(new Date())) + 1;

        var hoursLeft = data.time
                .stream()
                .filter(h -> Integer.parseInt(h.substring(h.length() - 5, h.length() - 3)) >= startingHour)
                .collect(Collectors.toList());

        for(var hour : hoursLeft) {
            var item = new HourlyWeatherItem(getContext(), null);
            linearLayout.addView(item);
            item.bindData(data, hour);
        }
    }

    @Override
    public void bindData(@Nullable WeatherModel.Hourly data) {
        this.data = data;
        displayData();
    }

    @Override
    public void bindData(@Nullable WeatherModel.Hourly data, @Nullable String indexValue) {
        bindData(data);
    }
}
