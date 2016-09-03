package com.baifan.bgank.gank.api;

import com.baifan.bgank.entity.Meizhi;
import com.baifan.bgank.gank.api.api.MeizhiApi;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by baifan on 16/9/1.
 */
public class GankNetwork {
    private final static String BASEURL = "http://gank.io/api/";

    private static MeizhiApi meizhiApi;

    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    public static MeizhiApi getMeizhiApi(){
        if(meizhiApi == null){
            Retrofit retrofit = new Retrofit.Builder()
                        .client(okHttpClient)
                        .baseUrl(BASEURL)
                        .addConverterFactory(gsonConverterFactory)
                        .addCallAdapterFactory(rxJavaCallAdapterFactory)
                        .build();
            meizhiApi = retrofit.create(MeizhiApi.class);
        }
        return meizhiApi;
    }
}
