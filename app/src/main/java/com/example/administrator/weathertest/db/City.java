package com.example.administrator.weathertest.db;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/10/26 0026.
 */

public class City extends DataSupport {

    private int ID;
    @SerializedName("name")
    private String cityName;
    @SerializedName("id")
    private int cityCode;

    private int provinceId;

    public int getID() {
        return ID;
    }

    public int getCityCode() {
        return cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setID(int id) {
        this.ID= ID;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }
}

