package com.baifan.bgank.gank.api.api;

import android.database.Observable;

import com.baifan.bgank.entity.Gank;

import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by baifan on 16/9/2.
 */
public interface GankDetailApi {
    @GET("day/{year}/{month}/{day}")
    Observable<Gank> getGankList(@Path("year") String year, @Path("month") String month, @Path("day") String day);
}
