package com.example.apiapp.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.apiapp.Client;
import com.example.apiapp.interfaces.CurrentWeatherResponseListener;
import com.example.apiapp.R;
import com.example.apiapp.DataBoundView;
import com.example.apiapp.WeatherModel;
import com.example.apiapp.interfaces.HourlyWeatherResponseListener;

import java.util.ArrayList;

public class CurrentWeatherFragment extends Fragment implements CurrentWeatherResponseListener, HourlyWeatherResponseListener {

    public CurrentWeatherFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_weather, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        ViewSwitcher viewSwitcher = requireActivity().findViewById(R.id.vsViewSwitcher);

        Button button = requireActivity().findViewById(R.id.btnSwitchView);
        button.setOnClickListener(view -> viewSwitcher.showNext());

        currentWeatherViews.add(requireActivity().findViewById(R.id.viwCurrentGeneralWeather));
        currentWeatherViews.add(requireActivity().findViewById(R.id.viwCurrentDetailsWeather));
        hourlyWeatherViews.add(requireActivity().findViewById(R.id.viwHourlyWeather));

        Client.getCurrentWeather(this);
        Client.getHourlyWeather(this);
    }

    @Override
    public void onResponse(@Nullable WeatherModel.Current responseBody) {
        for(var view : currentWeatherViews){
            view.bindData(responseBody);
        }
    }

    @Override
    public void onResponse(@Nullable WeatherModel.Hourly responseBody) {
        for(var view : hourlyWeatherViews){
            view.bindData(responseBody);
        }
    }

    @Override
    public void onRawJsonResponse(String json) {
        //TODO: implement...
    }

    @Override
    public void handleResponseCode(int code, String errorMessage) {
        String message = "";

        switch (code){
            case 0:
                message = errorMessage;
                break;

            case 404:
                message = "Not found";
                break;
        }

        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}