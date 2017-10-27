package com.example.administrator.weathertest.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/10/26 0026.
 *
 */

public class Suggestion {
    @SerializedName("comf")
    public Comfort comfort;
    @SerializedName("cw")
    public CarWash carWash;
    @SerializedName("sport")
    public Sport sport;
    public class CarWash{
        @SerializedName("tet")
        public String info;
    }
    public class Comfort{
        @SerializedName("txt")
        public String info;
    }
    public class Sport{
        @SerializedName("txt")
        public String info;
    }

}
