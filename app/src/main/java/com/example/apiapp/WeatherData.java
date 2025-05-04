package com.example.apiapp;

import java.util.ArrayList;

public class WeatherData {
    public float latitude;
    public float longitude;
    public float generationtime_ms;
    public int utc_offset_seconds;
    public String timezone;
    public String timezone_abbreviation;
    public float elevation;
    public Hourly hourly;
    //public HourlyUnits hourly_units;

    public class Hourly{
        public ArrayList<String> time;
        public ArrayList<Float> temperature_2m;
        public ArrayList<Float> apparent_temperature;
    }

    /*
    public class HourlyUnits{
        public String time;
        public String temperature_2m;
        public String apparent_temperature;
    }
    */

}
