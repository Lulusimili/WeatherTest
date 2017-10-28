package com.example.administrator.weathertest.db;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/27 0027.
 */

public class RootCity {
    private ArrayList<City> cityList;

    public void setCityList(ArrayList<City> cityList) {
        this.cityList = cityList;
    }

    public ArrayList<City> getCityList() {
        return cityList;
    }
}
