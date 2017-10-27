package com.example.administrator.weathertest.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/10/26 0026.
 */

public class Now {
    @SerializedName("tmp")
    public String temperature;
    @SerializedName("cond")
    public MoreCond more;
    public class MoreCond{
        @SerializedName("txt")
        public String info;
    }
}
