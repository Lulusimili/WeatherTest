package com.example.administrator.weathertest.db;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/27 0027.
 */

public class RootCounty {
    private ArrayList<County> countyList;

    public void setCountyList(ArrayList<County> countyList) {
        this.countyList = countyList;
    }

    public ArrayList<County> getCountyList() {
        return countyList;
    }
}
