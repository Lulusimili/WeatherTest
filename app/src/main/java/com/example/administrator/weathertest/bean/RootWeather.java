package com.example.administrator.weathertest.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/27 0027.
 *
 */

public class RootWeather {
    private ArrayList<Weather> weather;
    public void setweather(ArrayList<Weather>weather) {
        this.weather = weather;
    }
    public ArrayList<Weather> getweather() {
        return weather;
    }
}
