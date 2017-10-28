package com.example.administrator.weathertest.db;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/10/26 0026.
 */

public class County extends DataSupport {

    private int ID;
    @SerializedName("name")
    private String countyName;
    @SerializedName("weather_id")
    private String weatherId;
    @SerializedName("id")
    private int cityId;

    public int getID() {
        return ID;
    }

    public String getCountyName() {
        return countyName;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }
}
