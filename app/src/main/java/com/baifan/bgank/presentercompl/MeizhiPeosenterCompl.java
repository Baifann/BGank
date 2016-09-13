package com.baifan.bgank.presentercompl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Network;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.text.AndroidCharacter;
import android.view.View;
import android.widget.Toast;

import com.baifan.bgank.R;
import com.baifan.bgank.entity.Meizhi;
import com.baifan.bgank.entity.MeizhiList;
import com.baifan.bgank.gank.api.GankNetwork;
import com.baifan.bgank.ipresenter.MeizhiIpresenter;
import com.baifan.bgank.iview.MeizhiIView;
import com.baifan.bgank.ui.GankDetailActivity;
import com.baifan.bgank.ui.adapter.MeizhiAdapter;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Scheduler;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by baifan on 16/9/1.
 */
public class MeizhiPeosenterCompl implements MeizhiIpresenter {

    private Context mContext;

    private MeizhiIView mMeizhiIView;

    private Subscription subscription;

    private List<Meizhi> mList = new ArrayList<>();
    /**
     * 第几页
     */
    private int mPage;

    public MeizhiPeosenterCompl(MeizhiIView meizhiIView, Activity activity) {
        mMeizhiIView = meizhiIView;
        mContext = activity;
        mMeizhiIView.initEvents();
        mMeizhiIView.initRecyclerview();
        mMeizhiIView.initScroll();
        mMeizhiIView.loadRefresh();
    }

    @Override
    public MeizhiList getMeizhiList(String type, String pageSize, String page) {
        mPage = Integer.valueOf(page);
        subscription = GankNetwork.getMeizhiApi()
                .getMeizhi(type, pageSize, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return null;
    }

    @Override
    public void destroy() {
        mMeizhiIView = null;
    }

    @Override
    public void onItemClick(int position, View v) {
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                (Activity) mContext, v, mContext.getString(R.string.transition_meizhi));
        Meizhi meizhi = mList.get(position);
        Intent intent = new Intent(mContext, GankDetailActivity.class);
        intent.putExtra("meizhi", meizhi);
        ActivityCompat.startActivity((Activity) mContext, intent, compat.toBundle());
    }

    @Override
    public void loadMore() {
        mMeizhiIView.loadMore();
    }

    Observer<MeizhiList> observer = new Observer<MeizhiList>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            mMeizhiIView.handleFailGetMeizhi(e.toString());
        }

        @Override
        public void onNext(MeizhiList meizhiList) {
            mMeizhiIView.onLoaded();
            if (mList == null || mList.size() == 0) {
                mMeizhiIView.initAdatper();
            }
            if (mPage == 1) {
                mList = meizhiList.getResults();
            } else {
                mList.addAll(meizhiList.getResults());
            }
            mMeizhiIView.handleSuccessGetMeizhi(mList);
        }
    };
}
