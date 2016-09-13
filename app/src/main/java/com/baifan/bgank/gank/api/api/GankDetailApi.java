package com.baifan.bgank.gank.api.api;


import com.baifan.bgank.entity.Gank;
import com.baifan.bgank.entity.GankList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by baifan on 16/9/2.
 */
public interface GankDetailApi {
    @GET("day/{year}/{month}/{day}")
    Observable<GankList> getGankList(@Path("year") String year, @Path("month") String month, @Path("day") String day);
}
