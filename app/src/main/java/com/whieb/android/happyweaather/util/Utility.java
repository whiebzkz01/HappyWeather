package com.whieb.android.happyweaather.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.whieb.android.happyweaather.db.City;
import com.whieb.android.happyweaather.db.County;
import com.whieb.android.happyweaather.db.Province;
import com.whieb.android.happyweaather.gson.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yushanshan on 2017/3/16.
 */

public class Utility {

    /**
     * 解析和处理服务器返回的省级数据
     */
    public static boolean handleProvinceResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvice = new JSONArray(response);
                for (int i = 0; i < allProvice.length(); i++) {
                    JSONObject provinceObject = allProvice.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的市级数据
     */
    public static boolean handleCityResponse(String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvice = new JSONArray(response);
                for (int i = 0; i < allProvice.length(); i++) {
                    JSONObject provinceObject = allProvice.getJSONObject(i);
                    City city = new City();
                    city.setCityName(provinceObject.getString("name"));
                    city.setCityCode(provinceObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    /**
     * 解析和处理服务器返回的县级数据
     */
    public static boolean handleCountyResponse(String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvice = new JSONArray(response);
                for (int i = 0; i < allProvice.length(); i++) {
                    JSONObject provinceObject = allProvice.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(provinceObject.getString("name"));
                    county.setWeatherId(provinceObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 返回的JSon数据解析成Weather实体类
     * @param response
     * @return
     */
    public static Weather handleWeatherResponse(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return  new Gson().fromJson(weatherContent,Weather.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

}
