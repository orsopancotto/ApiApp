package com.example.apiapp;

import android.os.Bundle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    public static final String baseUrl = "https://api.open-meteo.com/v1/";
    private TextView textView;
    private Button getButton;

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

        Client.createClient(getApplicationContext());

        textView = findViewById(R.id.textView);

        getButton = findViewById(R.id.getBtn);
        getButton.setOnClickListener(this::getWeather);
    }

    private void getWeather(View view){
        Call<WeatherData> call = Client
                .getClient()
                .getController()
                .getWeatherData();
        call.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response){
                if(response.code() != 200){
                    MessagesDisplayer.printMessage(getApplicationContext(), "Cassofica qualcosa è anato storto :(\nCodice: " + response.code(), 0);
                    return;
                }

                if (response.body() == null) {
                    MessagesDisplayer.printMessage(getApplicationContext(), "Cassofica la risposta è nulla :(", 0);
                    return;
                }

                int currentIndex = response
                        .body()
                        .hourly
                        .time
                        .indexOf("2025-05-05T01:00");

                String result = "current temperature: " + response.body().hourly.temperature_2m.get(currentIndex) +
                        "\n current apparent temperature: " + response.body().hourly.apparent_temperature.get(currentIndex);

                textView.setText(result);
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable throwable) {
                MessagesDisplayer.printMessage(getApplicationContext(), "Cassofica richiesta fallita :(\nMessaggio: " + throwable.getMessage(), 0);
            }
        });
    }
}