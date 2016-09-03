package com.baifan.bgank.gank.api.api;

import com.baifan.bgank.entity.MeizhiList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by baifan on 16/9/1.
 */
public interface MeizhiApi {
    @GET("data/{type}/{pageSize}/{page}")
    Observable<MeizhiList> getMeizhi(@Path("type") String type, @Path("pageSize") String pageSize, @Path("page") String page);
}
