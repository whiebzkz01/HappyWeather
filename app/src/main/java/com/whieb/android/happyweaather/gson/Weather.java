package com.whieb.android.happyweaather.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zhukongzhen on 2017/3/17.
 */

public class Weather {

    public String status;

    public Basic basic;

    public AQI aqi;

    public Now now;

    public Suggestion suggestion;

    @SerializedName("daily_forecast")
    public List<Forecast> forecastList;
}
