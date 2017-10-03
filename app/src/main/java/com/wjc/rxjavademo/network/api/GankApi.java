package com.wjc.rxjavademo.network.api;

import com.wjc.rxjavademo.model.GankBeautyResult;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by WJC on 2017/10/3.
 */

public interface GankApi {
    @GET("data/福利/{number}/{page}")
    Observable<GankBeautyResult> getBeauties (@Path("number")int number,@Path("page") int page);
}
