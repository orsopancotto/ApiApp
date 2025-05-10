package com.example.apiapp.enums;

import java.util.Arrays;
import java.util.List;

public enum DailyQueryParam {
    TEMPERATURE_2M_MAX,
    TEMPERATURE_2M_MIN,
    APPARENT_TEMPERATURE_MAX,
    APPARENT_TEMPERATURE_MIN,
    PRECIPITATION_SUM,
    WEATHER_CODE,
    SUNRISE,
    SUNSET;

    public final static List<DailyQueryParam> all = Arrays.asList(
            TEMPERATURE_2M_MAX,
            TEMPERATURE_2M_MIN,
            APPARENT_TEMPERATURE_MAX,
            APPARENT_TEMPERATURE_MIN,
            PRECIPITATION_SUM,
            WEATHER_CODE,
            SUNRISE,
            SUNSET
    );
}