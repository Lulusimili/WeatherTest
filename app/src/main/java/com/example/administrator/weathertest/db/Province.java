package com.example.administrator.weathertest.db;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.DataSupport;

import static android.R.attr.id;

/**
 * Created by Administrator on 2017/10/26 0026.
 *
 */

public class Province extends DataSupport {
    private int ID;
    @SerializedName("name")
    private String provinceName;

    @SerializedName("id")
    private int provinceCode;

    public int getID() {
        return ID;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }
}
