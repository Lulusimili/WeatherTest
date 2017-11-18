package com.example.administrator.weathertest.view;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.weathertest.R;
import com.example.administrator.weathertest.WeatherService;
import com.example.administrator.weathertest.bean.Forecast;
import com.example.administrator.weathertest.bean.Weather;
import com.example.administrator.weathertest.db.City;
import com.example.administrator.weathertest.utils.HandleRequestUtility;
import com.example.administrator.weathertest.utils.HttpUtil;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class ForecastActivity extends AppCompatActivity {
    /**
     * 标题部分
     */
    private TextView cityName;
    private TextView updateTime;
    private Button homeButton;
    /**
     * 当前天气
     */
    private TextView degreeNow;
    private TextView weatherInfoNow;
    /**
     * 预报天气
     */
    private LinearLayout forecastLinearLayout;

    /***
     * 空气质量
     */
    private TextView AQIText;
    private TextView pm25Text;
    /**
     * 生活建议
     */
    private TextView comfortText;
    private TextView washText;
    private TextView sportText;

    private final String BASE_URL ="http://guolin.tech/api/weather?cityid=";
    private final String KEY="&key=8c01328aec5e46a59addc2d91db89af9";
    private ImageView bingBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_activity);
        initView();

        String weatherId=getIntent().getStringExtra("weatherId");
        queryWeather(BASE_URL,weatherId);
    }

    private void initView() {
        homeButton = (Button) findViewById(R.id.home_button);
        cityName = (TextView) findViewById(R.id.city_name);
        updateTime = (TextView) findViewById(R.id.update_time);
        degreeNow = (TextView) findViewById(R.id.degree);
        weatherInfoNow = (TextView) findViewById(R.id.weather_info);
        forecastLinearLayout = (LinearLayout) findViewById(R.id.forecast_layout);
        AQIText = (TextView) findViewById(R.id.aqi);
        pm25Text = (TextView) findViewById(R.id.pm25);
        comfortText = (TextView) findViewById(R.id.comfort);
        washText = (TextView) findViewById(R.id.wash);
        sportText = (TextView) findViewById(R.id.sports);
        bingBackground=(ImageView)findViewById(R.id.bing_background);
    }

    private void queryWeather(String baseUrl,String weatherId){
        String url=baseUrl+weatherId+KEY;
        Log.d("URL",url);
        HttpUtil.sendOkHttpRequest(url, new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ForecastActivity.this,"获取天气失败！",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                final String resoponseText=response.body().string();
                final Weather weatherRquest= HandleRequestUtility.handleWeatherRequest(resoponseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(weatherRquest!=null){
                            showWeather(weatherRquest);
                        }
                        else{
                            Toast.makeText(ForecastActivity.this,"获取天气失败！lllll",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        loadbingBackground();
    }
    private void showWeather(Weather weather){
        String name=weather.basic.cityName;
        String time=weather.basic.update.updateTime.split(" ")[1];
        String degree=weather.now.temperature+"℃";
        String info=weather.now.more.info;
        /**
         * 设置头部当前天气*/
        cityName.setText(name);
        degreeNow.setText(degree);
        updateTime.setText(time);
        weatherInfoNow.setText(info);
        /**
         * 遍历未来天气信息*/
        forecastLinearLayout.removeAllViews();
        for (Forecast forecast:weather.forecastList){
            View view= LayoutInflater.from(this).
                    inflate(R.layout.forecast_item,forecastLinearLayout,false);
              TextView  dateText = view.findViewById(R.id.date);
              TextView  infoText = view.findViewById(R.id.info);
              TextView minDegree = view.findViewById(R.id.min_degree);
              TextView maxDegee = view.findViewById(R.id.max_degree);
              dateText.setText(forecast.date);
              infoText.setText(forecast.more.info);
              Log.d("0000",forecast.more.info);
              minDegree.setText(forecast.temperature.min);
              maxDegee.setText(forecast.temperature.max);
              forecastLinearLayout.addView(view);
        }
        if(weather.aqi!=null){
            AQIText.setText(weather.aqi.city.aqi);
            pm25Text.setText(weather.aqi.city.pm25);
        }
        String comf="舒适度："+weather.suggestion.comfort.info;
        String wash="洗车系数："+weather.suggestion.carWash.info;
        String sport="运动建议："+weather.suggestion.sport.info;
        comfortText.setText(comf);
        washText.setText(wash);
        sportText.setText(sport);

    }
    private void loadbingBackground(){
        String url="http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(url, new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
               e.printStackTrace();
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                  final String pictureUrl=response.body().string();
                  runOnUiThread(new Runnable() {
                      @Override
                      public void run() {
                          Glide.
                                  with(ForecastActivity.this)
                                  .load(pictureUrl)
                                  .into(bingBackground);
                          Log.d("8888","加载"+pictureUrl);
                      }
                  });
            }
        });
    }
}

//    private void queryWeatherFromServer(String baseUrl, String weatherId) {
//        Call<Weather> call = HttpUtil.sendHttpRequest(baseUrl).getWeather(weatherId);
//        call.enqueue(new Callback<Weather>() {
//            @Override
//            public void onResponse(Call<Weather> call, Response<Weather> response) {
//                Weather weather=response.body();
//                if (weather != null && weather.staus.equals("ok")) {
//                    showWeather(weather);
//                } else {
//                    Toast.makeText(ForecastActivity.this, "获取天气失败", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Weather> call, Throwable t) {
//                t.printStackTrace();
//                Toast.makeText(ForecastActivity.this, "获取天气失败lalalala", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }