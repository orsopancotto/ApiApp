package com.example.apiapp;

import android.os.Bundle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import java.util.Map;
import java.util.Optional;


import android.os.Bundle;

import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.apiapp.enums.WeatherCode;

public class MainActivity extends ClientActivity {
    private TextView tvResponse;
    private Button fetchDataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        client = new Client();

        tvResponse = findViewById(R.id.tvResponse);

        fetchDataButton = findViewById(R.id.getBtn);
        fetchDataButton.setOnClickListener(view -> client.getCurrentWeather(this));
        //fetchDataButton.setOnClickListener(view -> client.getRawJson(this));
    }

    @Override
    public void displayResponseBody(WeatherModel responseBody){
        String text = "";

        if(responseBody == null || responseBody.current == null){
            text += "No Info";
        }
        else{
            text += "Weather: " + WeatherCode.getCode(responseBody.current.weather_code).toString() + "\n";
            text += "Temperature: " + responseBody.current.temperature_2m + "°C" + "\n";
            text += "Apparent temperature: " + responseBody.current.apparent_temperature + "°C" + "\n";
            text += responseBody.current.is_day == 1 ? "Daylight" : "Night";
        }

        tvResponse.setText(text);
    }

    @Override
    public void displayResponseBody(String responseBody){
        String text = "";

        if(responseBody == null){
            text += "No Info";
        }
        else{
            text = responseBody;
        }

        tvResponse.setText(text);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        client.cancelWebRequests();
    }
}
