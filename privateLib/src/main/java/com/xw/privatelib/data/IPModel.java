package com.xw.privatelib.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class IPModel implements Serializable {

    @SerializedName("cip")
    public String cip;

    @SerializedName("cid")
    public String cid;

    @SerializedName("cname")
    public String cname;
}
