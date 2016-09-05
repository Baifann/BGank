package com.baifan.bgank.presentercompl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.baifan.bgank.entity.Gank;
import com.baifan.bgank.entity.GankList;
import com.baifan.bgank.entity.MeizhiList;
import com.baifan.bgank.ipresenter.GankDetailIpresenter;
import com.baifan.bgank.iview.GankDetailIView;
import com.baifan.bgank.ui.GankDetailActivity;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;

/**
 * Created by baifan on 16/9/2.
 */
public class GankDetailPresenterCompl implements GankDetailIpresenter {
    private Context mContext;

    private GankDetailIView mGankDetailIView;

    private List<Gank> mList = new ArrayList<>();

    public GankDetailPresenterCompl(GankDetailActivity activity) {
        mGankDetailIView = (GankDetailIView) activity;
        mContext = activity;
    }

    @Override
    public void destroy() {
        mGankDetailIView = null;
    }

    @Override
    public void getDataFromIntent(Intent intent) {

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
            mGankDetailIView.onLoaded();
            if (mList == null || mList.size() == 0) {
                mList = gankList.getResults();
                mGankDetailIView.handleSuccessGankDetail(mList);
            }
        }
    };
}
