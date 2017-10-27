package com.example.administrator.weathertest.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/10/26 0026.
 */

public class AQI {
    public AQICity city;
    public class AQICity{
        @SerializedName("aqi")
        public String aqi;
        @SerializedName("pm25")
        public String pm25;
    }
}
