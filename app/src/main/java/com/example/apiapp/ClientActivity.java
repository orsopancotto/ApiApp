package com.example.apiapp;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public abstract class ClientActivity extends AppCompatActivity {
    protected Client client;

    public abstract void displayResponseBody(WeatherModel responseBody);

    public abstract void displayResponseBody(String rawJson);

    public void printQuickMessage(String message, int duration){
        Toast.makeText(getApplicationContext(), message, duration).show();
    }
}
