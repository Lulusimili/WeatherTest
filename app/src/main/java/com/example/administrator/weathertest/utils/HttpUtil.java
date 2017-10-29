package com.example.administrator.weathertest.utils;

import com.example.administrator.weathertest.WeatherService;
import com.example.administrator.weathertest.bean.Weather;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class HttpUtil {
    public static WeatherService sendHttpRequest(String baseUrl){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        WeatherService service=retrofit.create(WeatherService.class);
        return service;
    }
}
