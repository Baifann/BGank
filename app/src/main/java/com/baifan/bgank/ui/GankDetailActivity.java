package com.baifan.bgank.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.baifan.bgank.R;
import com.baifan.bgank.entity.Gank;
import com.baifan.bgank.entity.GankTypeDetailList;
import com.baifan.bgank.ipresenter.GankDetailIpresenter;
import com.baifan.bgank.iview.GankDetailIView;
import com.baifan.bgank.presentercompl.GankDetailPresenterCompl;
import com.baifan.bgank.ui.adapter.GankDetailAdapter;
import com.baifan.bgank.util.DLog;
import com.baifan.bgank.util.ImageLoaderUtil;
import com.baifan.bgank.util.ListUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by baifan on 16/9/2.
 */
public class GankDetailActivity extends AppCompatActivity implements GankDetailIView,
        GankDetailAdapter.OnSelectListener {
    @Bind(R.id.ly_loading)
    LinearLayout mLyLoading;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.img_meizhi)
    ImageView mImgMeizhi;

    @Bind(R.id.lv_android)
    ListView mLvAndroid;

    @Bind(R.id.ly_android)
    LinearLayout mLyAndroid;

    @Bind(R.id.lv_iOS)
    ListView mLvIOS;

    @Bind(R.id.ly_ios)
    LinearLayout mLyIOS;

    @Bind(R.id.lv_web)
    ListView mLvWeb;

    @Bind(R.id.ly_web)
    LinearLayout mLyWeb;

    @Bind(R.id.lv_app)
    ListView mLvApp;

    @Bind(R.id.ly_app)
    LinearLayout mLyAPP;

    @Bind(R.id.lv_xia)
    ListView mLvXia;

    @Bind(R.id.ly_xia)
    LinearLayout mLyXia;

    @Bind(R.id.lv_resource)
    ListView mLvResource;

    @Bind(R.id.ly_resource)
    LinearLayout mLyResource;

    @Bind(R.id.lv_vedio)
    ListView mLvVedio;

    @Bind(R.id.ly_vedio)
    LinearLayout mLyVedio;

    private GankDetailIpresenter mGankDetailIpresenter;

    private GankDetailAdapter mAndroidAdapter;
    private GankDetailAdapter mIOSAdapter;
    private GankDetailAdapter mWebAdapter;
    private GankDetailAdapter mAppAdapter;
    private GankDetailAdapter mXiaAdapter;
    private GankDetailAdapter mResourceAdapter;
    private GankDetailAdapter mVedioAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gank_detail);
        ButterKnife.bind(this);
        initDatas();
    }

    private void initDatas() {
        mGankDetailIpresenter = new GankDetailPresenterCompl(this);
        mGankDetailIpresenter.getDataFromIntent(getIntent());
    }

    @Override
    public void handleSuccessGankDetail(GankTypeDetailList list) {
        setHideorShowLvAndroid(list.getAndroidList(), mLyAndroid, mAndroidAdapter);
        setHideorShowLvAndroid(list.getIosList(), mLyIOS, mIOSAdapter);
        setHideorShowLvAndroid(list.getWebList(), mLyWeb, mWebAdapter);
        setHideorShowLvAndroid(list.getResourceList(), mLyResource, mResourceAdapter);
        setHideorShowLvAndroid(list.getAppList(), mLyAPP, mAppAdapter);
        setHideorShowLvAndroid(list.getXiaList(), mLyXia, mXiaAdapter);
        setHideorShowLvAndroid(list.getVedioList(), mLyVedio, mVedioAdapter);
    }

    private void setHideorShowLvAndroid(List<Gank> list, LinearLayout linearLayout, GankDetailAdapter adapter) {
        if (ListUtil.isEmpty(list)) {
            linearLayout.setVisibility(View.GONE);
        } else {
            linearLayout.setVisibility(View.VISIBLE);
            adapter.setList(list);
        }
    }

    @Override
    public void handleFailGankDetail(String error) {
        DLog.i("error:" + error);
    }

    @Override
    public void onLoaded() {
        mLyLoading.setVisibility(View.GONE);
    }

    @Override
    public void setMeizhiImage(String meizhiUrl) {
        ImageLoaderUtil.loadImage(meizhiUrl, mImgMeizhi);
    }

    @Override
    public void initAdapter() {
        mAndroidAdapter = new GankDetailAdapter();
        mIOSAdapter = new GankDetailAdapter();
        mWebAdapter = new GankDetailAdapter();
        mAppAdapter = new GankDetailAdapter();
        mXiaAdapter = new GankDetailAdapter();
        mVedioAdapter = new GankDetailAdapter();
        mResourceAdapter = new GankDetailAdapter();
        mLvAndroid.setAdapter(mAndroidAdapter);
        mLvIOS.setAdapter(mIOSAdapter);
        mLvWeb.setAdapter(mWebAdapter);
        mLvResource.setAdapter(mResourceAdapter);
        mLvXia.setAdapter(mXiaAdapter);
        mLvVedio.setAdapter(mVedioAdapter);
    }

    @Override
    public void initEvents() {
        mAndroidAdapter.setOnSelectListener(this);
        mIOSAdapter.setOnSelectListener(this);
        mWebAdapter.setOnSelectListener(this);
        mAppAdapter.setOnSelectListener(this);
        mXiaAdapter.setOnSelectListener(this);
        mVedioAdapter.setOnSelectListener(this);
        mResourceAdapter.setOnSelectListener(this);
    }

    @Override
    protected void onDestroy() {
        mGankDetailIpresenter.destroy();
        super.onDestroy();
    }

    @Override
    public void onSelect(String url) {
        mGankDetailIpresenter.goToWebViewActivity(url);
    }
}
