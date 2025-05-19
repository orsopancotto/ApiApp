package com.example.apiapp;

import java.util.ArrayList;

public class WeatherModel {
    public float latitude;
    public float longitude;
    public float generationtime_ms;
    public int utc_offset_seconds;
    public String timezone;
    public String timezone_abbreviation;
    public int elevation;
    public Current current;
    public Hourly hourly;

    public class Current{
        public String time;
        public int interval;
        public float temperature_2m;
        public int weather_code;
        public float precipitation;
        public float rain;
        public float showers;
        public float snowfall;
        public float apparent_temperature;
        public int is_day;
        public float wind_speed_10m;
        public int wind_direction_10m;
        public float wind_gusts_10m;
        public int relative_humidity_2m;
    }

    public class Hourly{
        public ArrayList<String> time;
        public ArrayList<Float> temperature_2m;
        public ArrayList<Float> apparent_temperature;
        public ArrayList<Integer> precipitation_probability;
        public ArrayList<Integer> weather_code;
        public ArrayList<Integer> is_day;
    }

    public class Daily{
        public ArrayList<String> time;
        public ArrayList<Integer> weather_code;
        public ArrayList<Float> apparent_temperature_max;
        public ArrayList<Float> apparent_temperature_min;
        public ArrayList<String> sunrise;
        public ArrayList<String> sunset;
        public ArrayList<Float> precipitation_sum;
        public ArrayList<Float> temperature_2m_min;
        public ArrayList<Float> temperature_2m_max;
    }
}