package com.xw.privatelib.utils;

import android.os.Build;

public class DeviceUtill {

    /**
     * 获取手机型号
     * @return  手机型号
     */
    public static String getSystemModel() {
        return Build.MODEL;
    }

    /**
     * 获取手机厂商
     * @return  手机厂商
     */
    public static String getDeviceBrand() {
        return Build.BRAND;
    }

}
