package com.wjc.rxjavademo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by WJC on 2017/10/2.
 */

public class GankBeautyResult {
    public boolean error;

    public @SerializedName("results")
    List<GankBeauty>  beauties;
}
