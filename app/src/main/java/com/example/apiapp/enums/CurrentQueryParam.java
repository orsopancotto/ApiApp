package com.example.apiapp.enums;

import java.util.Arrays;
import java.util.List;

public enum CurrentQueryParam {
    TEMPERATURE_2M,
    APPARENT_TEMPERATURE,
    WEATHER_CODE,
    PRECIPITATION,
    RAIN,
    SHOWERS,
    SNOWFALL,
    IS_DAY,
    WIND_SPEED_10M,
    WIND_DIRECTION_10M,
    WIND_GUSTS_10M,
    RELATIVE_HUMIDITY_2M;

    public final static List<CurrentQueryParam> all = Arrays.asList(
            TEMPERATURE_2M,
            APPARENT_TEMPERATURE,
            WEATHER_CODE,
            PRECIPITATION,
            RAIN,
            SHOWERS,
            SNOWFALL,
            IS_DAY,
            WIND_SPEED_10M,
            WIND_DIRECTION_10M,
            WIND_GUSTS_10M,
            RELATIVE_HUMIDITY_2M
    );
}