package com.example.apiapp;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

public abstract class HWDataBoundView extends DataBoundView{

    public HWDataBoundView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public abstract void bindData(@Nullable WeatherModel.Hourly data);

    public abstract void bindData(@Nullable WeatherModel.Hourly data, @NotNull String indexValue);
}
