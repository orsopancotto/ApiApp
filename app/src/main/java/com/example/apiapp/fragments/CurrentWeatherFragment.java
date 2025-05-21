package com.example.apiapp.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.apiapp.Client;
import com.example.apiapp.OnResponseListener;
import com.example.apiapp.R;
import com.example.apiapp.UI.DataBoundView;
import com.example.apiapp.UI.HourlyWeatherItem;
import com.example.apiapp.WeatherModel;

import java.util.ArrayList;

public class CurrentWeatherFragment extends Fragment implements OnResponseListener {
    private final ArrayList<DataBoundView> views = new ArrayList<>();


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

        views.add(requireActivity().findViewById(R.id.viwCurrentGeneralWeather));
        views.add(requireActivity().findViewById(R.id.viwCurrentDetailsWeather));
        Client.getCurrentWeather(this);
    }

    @Override
    public void onWeatherDataResponse(@Nullable WeatherModel responseBody) {
        for(var view : views){
            view.bindData(responseBody, null);
        }

        try{
            LinearLayout layout = requireActivity().findViewById(R.id.llHourlyWeather);
            for(var hour : responseBody.hourly.time){
                var item = new HourlyWeatherItem(requireContext(), null);
                layout.addView(item);
                item.bindData(responseBody, hour);
            }
        } catch (Exception e) {
            Toast.makeText(requireContext(), "Err: " + e.getCause(), Toast.LENGTH_LONG).show();
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