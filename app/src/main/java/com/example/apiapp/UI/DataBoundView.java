package com.example.apiapp.UI;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.apiapp.WeatherModel;

public abstract class DataBoundView extends ConstraintLayout {

    public DataBoundView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater
                .from(context)
                .inflate(setViewResource(), this, true);
        setWidgetsReferences();
    }

    protected abstract int setViewResource();
    protected abstract void setWidgetsReferences();
    protected abstract void displayData();
    public abstract void bindData(@Nullable WeatherModel data, @Nullable String indexValue);
}
