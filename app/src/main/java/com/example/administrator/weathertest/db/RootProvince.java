package com.example.administrator.weathertest.db;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/27 0027.
 */

public class RootProvince {

    private ArrayList<Province> provinceList;
    public void setProvinceList(ArrayList<Province> provinceList) {
        this.provinceList = provinceList;
    }

    public ArrayList<Province> getProvinceList() {
        return provinceList;
    }
}
