package com.example.administrator.weathertest;


import com.example.administrator.weathertest.db.City;
import com.example.administrator.weathertest.db.County;
import com.example.administrator.weathertest.db.Province;

import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/10/27 0027.
 *
 */

public interface WeatherService {
    @GET("api/china")
    Observable<ArrayList<Province>> getProvinces();
//    @GET("api/china")
//    Call<RootProvince>getProvince();

    @GET("api/china/{provinceCode}")
    Observable<ArrayList<City>> getCities(@Path("provinceCode") int provinceCode);

    @GET("api/china/{provinceCode}/{cityCode}")
    Observable<ArrayList<County>> getCounties(@Path("provinceCode") int provinceCode,
                                              @Path("cityCode") int cityCode);
    @GET("api/weather?cityid={cityid}&key=8c01328aec5e46a59addc2d91db89af9")
    Observable<ArrayList<County>>getWeather(@Path("cityid")String id);
}

