package com.example.administrator.weathertest.utils;

import android.util.Log;

import com.example.administrator.weathertest.bean.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

/**将获取的天气数据实例化*/
public class HandleRequestUtility {
    public static Weather handleWeatherRequest(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherString = jsonArray.getJSONObject(0).toString();
            System.out.print(88888);
            System.out.print(weatherString);
            return new Gson().fromJson(weatherString, Weather.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
