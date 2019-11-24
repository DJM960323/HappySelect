package com.example.happyselect.Data;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2019-11-24.
 * @author DengJianMing
 * @describe
 */
public class SaveData {
    private final String TAG = "DataSave";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public SaveData(Context mContext, String preferenceName) {
        preferences = mContext.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    /**
     * save json string of data list to share preference
     * @param tag
     * @param datalist
     */
    public <T> void setDataList(String tag, List<T> datalist) {
        if (null == datalist)
            return;

        Gson gson = new Gson();
        //change datalist to json
        String strJson = gson.toJson(datalist);
        Log.d(TAG,"setDataList, json:"+strJson);
        editor.clear();
        editor.putString(tag, strJson);
        editor.commit();
    }

    /**
     * get data List from share preferences
     * @param tag share preferences data tag
     * @param cls target list element object class
     * @return list
     */
    public <T> List<T> getDataList(String tag, Class<T> cls) {
        List<T> datalist=new ArrayList<T>();
        String strJson = preferences.getString(tag, null);
        if (null == strJson) {
            return datalist;
        }
        Log.d(TAG,"getDataList, json:"+strJson);
        try {
            Gson gson = new Gson();
            JsonArray array = new JsonParser().parse(strJson).getAsJsonArray();
            for (JsonElement jsonElement : array) {
                datalist.add(gson.fromJson(jsonElement, cls));
            }
        } catch (Exception e) {
            Log.e(TAG,"Exception : "+e.getMessage());
        }
        return datalist;
    }

    /**
     * save json string of data to share preference
     * @param tag
     * @param data object
     */
    public <T> void setData(String tag, T data) {
        if (null == data)
            return;

        Gson gson = new Gson();
        //change data to json
        String strJson = gson.toJson(data);
        Log.d(TAG,"setData, json:"+strJson);
        editor.clear();
        editor.putString(tag, strJson);
        editor.commit();
    }

    /**
     * get data from share preferences
     * @param tag share preferences data tag
     * @param cls target object class
     * @return target object or null if error happyed
     */
    public <T> T getData(String tag, Class<T> cls) {
        T data = null;
        String strJson = preferences.getString(tag, null);
        if (null == strJson) {
            return null;
        }
        Log.d(TAG,"getData, json:"+strJson);
        try {
            Gson gson = new Gson();
            JsonElement jsonElement = new JsonParser().parse(strJson);
            data = gson.fromJson(jsonElement, cls);
        } catch (Exception e) {
            Log.e(TAG,"Exception : "+e.getMessage());
        }
        return data;
    }
}
