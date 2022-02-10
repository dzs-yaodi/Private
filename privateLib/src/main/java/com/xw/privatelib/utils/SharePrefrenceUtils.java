package com.xw.privatelib.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;

public class SharePrefrenceUtils {

    private SharedPreferences pret;
    private static String AGREE_PROTECT = "agree_protect";

    public SharePrefrenceUtils(Context context){

        this.pret = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public String getAgreeProtect(Context context) {
        String message = "AGREE_PROTECT";
        PackageManager packageManager = context.getPackageManager();
        try {
            message =  message + "_" + packageManager.getApplicationInfo(context.getApplicationInfo().packageName,0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return message;
    }

    public void saveString(String key,String value){
        pret.edit().putString(key,value).apply();
    }

    public String getString(String key){
        return pret.getString(key,"");
    }

    public void saveBoolean(String key,boolean value){
        pret.edit().putBoolean(key,value).apply();
    }

    public boolean getBoolean(String key){
        return pret.getBoolean(key,false);
    }


}
