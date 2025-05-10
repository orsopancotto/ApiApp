package com.example.apiapp.enums;

import java.util.Map;

public enum WeatherCode {

    CLEAR_SKY,
    MAINLY_CLEAR,
    PARTLY_CLOUDY,
    OVERCAST,
    FOG,
    DEPOSITING_RIME_FOG,
    LIGHT_DRIZZLE,
    MODERATE_DRIZZLE,
    DENSE_DRIZZLE,
    LIGHT_FREEZING_DRIZZLE,
    DENSE_FREEZING_DRIZZLE,
    SLIGHT_RAIN,
    MODERATE_RAIN,
    HEAVY_RAIN,
    LIGHT_FREEZING_RAIN,
    HEAVY_FREEZING_RAIN,
    SLIGHT_SNOW_FALL,
    MODERATE_SNOW_FALL,
    HEAVY_SNOW_FALL,
    SNOW_GRAINS,
    SLIGHT_RAIN_SHOWERS,
    MODERATE_RAIN_SHOWERS,
    VIOLENT_RAIN_SHOWERS,
    SLIGHT_SNOW_SHOWERS,
    HEAVY_SNOW_SHOWERS,
    THUNDERSTORM,
    SLIGHT_HAIL_THUNDERSTORM,
    HEAVY_HAIL_THUNDERSTORM;

    private static final Map<Integer, WeatherCode> map = Map.ofEntries(
            Map.entry(0, CLEAR_SKY),
            Map.entry(1, MAINLY_CLEAR),
            Map.entry(2, PARTLY_CLOUDY),
            Map.entry(3, OVERCAST),
            Map.entry(45, FOG),
            Map.entry(48, DEPOSITING_RIME_FOG),
            Map.entry(51, LIGHT_DRIZZLE),
            Map.entry(53, MODERATE_DRIZZLE),
            Map.entry(55, DENSE_DRIZZLE),
            Map.entry(56, LIGHT_FREEZING_DRIZZLE),
            Map.entry(57, DENSE_FREEZING_DRIZZLE),
            Map.entry(61, SLIGHT_RAIN),
            Map.entry(63, MODERATE_RAIN),
            Map.entry(65, HEAVY_RAIN),
            Map.entry(66, LIGHT_FREEZING_RAIN),
            Map.entry(67, HEAVY_FREEZING_RAIN),
            Map.entry(71, SLIGHT_SNOW_FALL),
            Map.entry(73, MODERATE_SNOW_FALL),
            Map.entry(75, HEAVY_SNOW_FALL),
            Map.entry(77, SNOW_GRAINS),
            Map.entry(80, SLIGHT_RAIN_SHOWERS),
            Map.entry(81, MODERATE_RAIN_SHOWERS),
            Map.entry(82, VIOLENT_RAIN_SHOWERS),
            Map.entry(85, SLIGHT_SNOW_SHOWERS),
            Map.entry(86, HEAVY_SNOW_SHOWERS),
            Map.entry(95, THUNDERSTORM),
            Map.entry(96, SLIGHT_HAIL_THUNDERSTORM),
            Map.entry(99, HEAVY_HAIL_THUNDERSTORM)
    );

    public static WeatherCode getCode(int i){
        return map.get(i);
    }
    /*
    private final int value;
    private static final Map<Integer, WeatherCode> map = new HashMap<>();

    static {
        for(var code : WeatherCode.values()){
            map.put(code.value, code);
        }
    }

    WeatherCode(int value){
        this.value = value;
    }
    */
}