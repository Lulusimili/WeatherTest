package com.example.administrator.weathertest.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/10/26 0026.
 */

public class Basic {
    @SerializedName("city")
    public String cityName;
    @SerializedName("id")
    public String weatherId;
    @SerializedName("update")
    public Update update;
    public class Update{
        @SerializedName("loc")
        public String updateTime;
    }
}
