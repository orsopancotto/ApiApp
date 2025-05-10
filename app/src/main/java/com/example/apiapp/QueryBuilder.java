package com.example.apiapp;

import androidx.annotation.NonNull;

import com.example.apiapp.enums.CurrentQueryParam;
import com.example.apiapp.enums.DailyQueryParam;
import com.example.apiapp.enums.HourlyQueryParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QueryBuilder {
    private final float latitude;
    private final float longitude;
    private final String timezone;
    private final ArrayList<HourlyQueryParam> hourlyQueryParams;
    private final ArrayList<CurrentQueryParam> currentQueryParams;
    private final ArrayList<DailyQueryParam> dailyQueryParams;

    public static class Builder{
        private final float latitude;
        private final float longitude;
        private String timezone = "CET";
        private ArrayList<HourlyQueryParam> hourlyQueryParams;
        private ArrayList<CurrentQueryParam> currentQueryParams;
        private ArrayList<DailyQueryParam> dailyQueryParams;

        public Builder(float latitude, float longitude){
            this.latitude = latitude;
            this.longitude = longitude;
            this.hourlyQueryParams = new ArrayList<>();
            this.currentQueryParams = new ArrayList<>();
            this.dailyQueryParams = new ArrayList<>();
        }

        public Builder setTimezone(String timezone){
            this.timezone = timezone;
            return this;
        }

        public Builder addHourlyQueryParam(HourlyQueryParam param){
            this.hourlyQueryParams.add(param);
            return this;
        }

        public Builder setAllHourlyQueryParams(){
            this.hourlyQueryParams = new ArrayList<>(HourlyQueryParam.all);
            return this;
        }

        public Builder addCurrentQueryParam(CurrentQueryParam item){
            this.currentQueryParams.add(item);
            return this;
        }

        public Builder setAllCurrentQueryParams(){
            this.currentQueryParams = new ArrayList<>(CurrentQueryParam.all);
            return this;
        }

        public Builder addDailyQueryParam(DailyQueryParam param){
            this.dailyQueryParams.add(param);
            return this;
        }

        public Builder setAllDailyQueryParams(){
            this.dailyQueryParams = new ArrayList<>(DailyQueryParam.all);
            return this;
        }

        public QueryBuilder build(){
            return new QueryBuilder(this);
        }
    }

    private QueryBuilder(@NonNull Builder builder){
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
        this.timezone = builder.timezone;
        this.hourlyQueryParams = new ArrayList<>(builder.hourlyQueryParams);
        this.currentQueryParams = new ArrayList<>(builder.currentQueryParams);
        this.dailyQueryParams = new ArrayList<>(builder.dailyQueryParams);
    }


    public Map<String, String> createQuery(){
        Map<String, String> query = new HashMap<>();

        query.put("latitude", Float.toString(latitude));
        query.put("longitude", Float.toString(longitude));
        query.put("timezone", timezone);
        query.put("hourly", convertToQueryParams(hourlyQueryParams));
        query.put("current", convertToQueryParams(currentQueryParams));
        query.put("daily", convertToQueryParams(dailyQueryParams));

        return query;
    }

    private<T> String convertToQueryParams(ArrayList<T> list){
        if(list.isEmpty()) return "";

        StringBuilder stringBuilder = new StringBuilder();
        for(var item : list){
            stringBuilder
                    .append(item.toString().toLowerCase())
                    .append(",");
        }
        return stringBuilder.toString().substring(0, stringBuilder.length() - 1);
    }
}

