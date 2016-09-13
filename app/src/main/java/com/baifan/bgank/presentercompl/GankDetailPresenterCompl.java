package com.baifan.bgank.presentercompl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.baifan.bgank.entity.Gank;
import com.baifan.bgank.entity.GankList;
import com.baifan.bgank.entity.GankTypeDetailList;
import com.baifan.bgank.entity.Meizhi;
import com.baifan.bgank.entity.MeizhiList;
import com.baifan.bgank.gank.api.GankNetwork;
import com.baifan.bgank.ipresenter.GankDetailIpresenter;
import com.baifan.bgank.iview.GankDetailIView;
import com.baifan.bgank.ui.GankDetailActivity;
import com.baifan.bgank.ui.GankWebviewActivity;
import com.baifan.bgank.util.DLog;
import com.baifan.bgank.util.ListUtil;
import com.baifan.bgank.util.TimeUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by baifan on 16/9/2.
 */
public class GankDetailPresenterCompl implements GankDetailIpresenter {
    private Context mContext;

    private GankDetailIView mGankDetailIView;

    private GankTypeDetailList mList;

    private Subscription subscription;

    public GankDetailPresenterCompl(GankDetailActivity activity) {
        mGankDetailIView = (GankDetailIView) activity;
        mContext = activity;
        mGankDetailIView.initAdapter();
        mGankDetailIView.initEvents();
    }

    @Override
    public void destroy() {
        mGankDetailIView = null;
    }

    @Override
    public void getDataFromIntent(Intent intent) {
        Meizhi meizhi = (Meizhi) intent.getSerializableExtra("meizhi");
        DLog.i("接收到了:" + meizhi.toString());
        String[] timeArray = TimeUtil.getTimeArrayFromDate(meizhi.getCreatedAt());
        DLog.i("timeArray[0]:" + timeArray[0] + ",timeArray[1]:" + timeArray[1] + ",timeArray[2]:" + timeArray[2]);
        mGankDetailIView.setMeizhiImage(meizhi.getUrl());
        getGankDetailFromWeb(timeArray);
    }

    @Override
    public void goToWebViewActivity(String url) {
        Intent intent = new Intent(mContext, GankWebviewActivity.class);
        DLog.i("启动页面url:" + url);
        intent.putExtra("url", url);
        mContext.startActivity(intent);
    }

    private void getGankDetailFromWeb(String[] timeArray) {
        subscription = GankNetwork.getGankDetailApi()
                .getGankList(timeArray[0], timeArray[1], timeArray[2])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    Observer<GankList> observer = new Observer<GankList>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            mGankDetailIView.handleFailGankDetail(e.toString());
        }

        @Override
        public void onNext(GankList gankList) {
            DLog.i(gankList.toString());
            mGankDetailIView.onLoaded();
            mList = gankList.getResults();
            mGankDetailIView.handleSuccessGankDetail(mList);
        }
    };
}
