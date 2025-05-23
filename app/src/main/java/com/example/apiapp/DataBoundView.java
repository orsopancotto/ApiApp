package com.example.apiapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public abstract class DataBoundView extends ConstraintLayout {

    public DataBoundView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater
                .from(context)
                .inflate(getViewResource(), this, true);
        setWidgetsReferences();
    }

    protected abstract int getViewResource();
    protected abstract void setWidgetsReferences();
    protected abstract void displayData();
}
