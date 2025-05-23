package com.example.apiapp;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class CWDataBoundView extends DataBoundView{

    public CWDataBoundView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public abstract void bindData(@Nullable WeatherModel.Current data);
}
