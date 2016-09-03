package com.baifan.bgank.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.baifan.bgank.R;
import com.baifan.bgank.entity.Gank;
import com.baifan.bgank.ipresenter.GankDetailIpresenter;
import com.baifan.bgank.iview.GankDetailIView;
import com.baifan.bgank.presentercompl.GankDetailPresenterCompl;

import java.util.List;

/**
 * Created by baifan on 16/9/2.
 */
public class GankDetailActivity extends AppCompatActivity implements GankDetailIView {
    private GankDetailIpresenter mGankDetailIpresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gank_detail);
        initDatas();
    }

    private void initDatas() {
        mGankDetailIpresenter = new GankDetailPresenterCompl(this);
        mGankDetailIpresenter.getDataFromIntent(getIntent());
    }

    @Override
    public void handleSuccessGankDetail(List<Gank> list) {

    }

    @Override
    public void handleFailGankDetail(String error) {

    }

    @Override
    public void onLoaded() {

    }
}
