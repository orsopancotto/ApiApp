package com.example.apiapp.enums;

import java.util.Arrays;
import java.util.List;

public enum HourlyQueryParam {
    TEMPERATURE_2M,
    APPARENT_TEMPERATURE,
    PRECIPITATION_PROBABILITY,
    WEATHER_CODE;

    public final static List<HourlyQueryParam> all = Arrays.asList(
            TEMPERATURE_2M,
            APPARENT_TEMPERATURE,
            PRECIPITATION_PROBABILITY,
            WEATHER_CODE
    );
}