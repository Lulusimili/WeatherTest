package com.example.administrator.weathertest.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/27 0027.
 *
 */

public class RootWeather {
    private List<Weather> weather;
    public void setweather(List<Weather>weather) {
        this.weather = weather;
    }
    public List<Weather> getweather() {
        return weather;
    }

}
