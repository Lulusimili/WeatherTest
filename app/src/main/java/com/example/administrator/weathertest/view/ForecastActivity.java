package com.example.administrator.weathertest.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.weathertest.R;

public class ForecastActivity extends AppCompatActivity {
    /**
     * 标题部分
     * */
    private TextView cityName;
    private TextView updateTime;
    private Button homeButton;
    /**
     * 当前天气
     * */
    private TextView degreeNow;
    private TextView weatherInfoNow;
    /**
     * 预报天气
     * */
    private LinearLayout forecastLinearLayout;
    private TextView dateText;
    private TextView infoText;
    private TextView minDegree;
    private TextView maxDegee;
    /***
     * 空气质量
     */
    private TextView AQIText;
    private TextView pm25Text;
    /**
     * 生活建议*/
    private TextView comfortText;
    private TextView washText;
    private TextView sportText;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_activity);
        initView();
    }
    
    private void initView(){
        homeButton=(Button)findViewById(R.id.home_button);
        cityName=(TextView)findViewById(R.id.city_name);
        updateTime=(TextView)findViewById(R.id.update_time);
        degreeNow=(TextView)findViewById(R.id.degree);
        weatherInfoNow=(TextView)findViewById(R.id.weather_info);
        forecastLinearLayout=(LinearLayout)findViewById(R.id.forecast_layout);
        dateText=(TextView)findViewById(R.id.date);
        infoText=(TextView)findViewById(R.id.info);
        minDegree=(TextView)findViewById(R.id.min_degree);
        maxDegee=(TextView)findViewById(R.id.max_degree);
        AQIText=(TextView)findViewById(R.id.aqi);
        pm25Text=(TextView)findViewById(R.id.pm25);
        comfortText=(TextView)findViewById(R.id.comfort);
        washText=(TextView)findViewById(R.id.wash);
        sportText=(TextView)findViewById(R.id.sports);
    }
}
