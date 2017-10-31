package com.example.administrator.weathertest.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017/10/27 0027.
 */

public class Weather {
    @SerializedName("status")
    public String staus;
    @SerializedName("basic")
    public Basic basic;
    @SerializedName("aqi")
    public AQI aqi;
    @SerializedName("now")
    public Now now;
    @SerializedName("suggestion")
    public Suggestion suggestion;
    @SerializedName("daily_forecast")
    public List<Forecast> forecastList;
}
