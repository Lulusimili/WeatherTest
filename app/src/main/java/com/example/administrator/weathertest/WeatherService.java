package com.example.administrator.weathertest;

import android.widget.ListView;

import com.example.administrator.weathertest.bean.RootWeather;
import com.example.administrator.weathertest.bean.Weather;
import com.example.administrator.weathertest.db.City;
import com.example.administrator.weathertest.db.County;
import com.example.administrator.weathertest.db.Province;
import com.example.administrator.weathertest.db.RootCity;
import com.example.administrator.weathertest.db.RootCounty;
import com.example.administrator.weathertest.db.RootProvince;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/10/27 0027.
 *
 */

public interface WeatherService {
    @GET("api/china")
    Observable<RootProvince> getProvinces();

    @GET("api/china/{provinceCode}")
    Observable<RootCity> getCities(@Path("provinceCode") int provinceCode);

    @GET("api/china/{provinceCode}/{cityCode}")
    Observable<RootCounty> getCounties(@Path("provinceCode") int provinceCode,
                                   @Path("CityCode") int cityCode);
    @GET("api/weather?cityid={cityid}&key=8c01328aec5e46a59addc2d91db89af9")
    Observable<RootCounty>getWeather(@Path("cityid")String id);
}

