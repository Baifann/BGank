package com.baifan.bgank.presentercompl;

import android.app.Activity;
import android.content.Context;
import android.net.Network;
import android.text.AndroidCharacter;
import android.widget.Toast;

import com.baifan.bgank.R;
import com.baifan.bgank.entity.Meizhi;
import com.baifan.bgank.entity.MeizhiList;
import com.baifan.bgank.gank.api.GankNetwork;
import com.baifan.bgank.ipresenter.MeizhiIpresenter;
import com.baifan.bgank.iview.MeizhiIView;
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

    public MeizhiPeosenterCompl(MeizhiIView meizhiIView ,Activity activity) {
        mMeizhiIView = meizhiIView;
        mContext = activity;
        mMeizhiIView.initEvents();
        mMeizhiIView.initRecyclerview();
    }

    @Override
    public MeizhiList getMeizhiList(String type, String pageSize, String page) {
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
            if(mList == null || mList.size() == 0){
                mMeizhiIView.initAdatper();
            }
            mList = meizhiList.getResults();
            mMeizhiIView.handleSuccessGetMeizhi(mList);
        }
    };
}
