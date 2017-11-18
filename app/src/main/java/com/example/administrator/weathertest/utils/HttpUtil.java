package com.example.administrator.weathertest.utils;

import com.example.administrator.weathertest.WeatherService;
import com.example.administrator.weathertest.bean.Weather;

import okhttp3.OkHttpClient;
import okhttp3.Request;
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
    public static void sendOkHttpRequest(String url,
                                         okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(url).build();
        client.newCall(request).enqueue(callback);
    }
}
