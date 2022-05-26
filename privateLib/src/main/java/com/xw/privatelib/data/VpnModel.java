package com.xw.privatelib.data;

import com.google.gson.annotations.SerializedName;

public class VpnModel {

    @SerializedName("status")
    public String status;

    @SerializedName("data")
    public Data data;

    public static class Data {

        @SerializedName("ip")
        public String ip;

        @SerializedName("ip_num")
        public String ip_num;

        @SerializedName("country_code")
        public String country_code;

        @SerializedName("country_name")
        public String country_name;

        @SerializedName("city")
        public String city;

        @SerializedName("isp")
        public String isp;

        @SerializedName("block")
        public int block;
    }
}
