package com.example.administrator.weathertest.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/10/26 0026.
 * 
 */

public class Forecast {
    @SerializedName("date")
    public String date;
    @SerializedName("tmp")
    public Temperature temperature;
    @SerializedName("cond")
    public More more;
    public class Temperature{
        @SerializedName("max")
        public String max;
        @SerializedName("min")
        public String min;
    }
    public  class More{
        @SerializedName("txt_d")
         public String info;
    }
}
