package com.baifan.bgank.presentercompl;

import android.app.Activity;
import android.content.Intent;

import com.baifan.bgank.ipresenter.GankWebviewIpresenter;
import com.baifan.bgank.iview.GankWebviewIView;

/**
 * Created by baifan on 16/9/13.
 */
public class GankWebviewIPresenterCompl implements GankWebviewIpresenter {
    private GankWebviewIView mGankWebviewIView;

    public GankWebviewIPresenterCompl(Activity activity){
        mGankWebviewIView = (GankWebviewIView) activity;
        mGankWebviewIView.initWebview();
        mGankWebviewIView.setUpWebviewProgress();
    }

    @Override
    public void getDataFromIntent(Intent intent) {
        String url = intent.getStringExtra("url");
        mGankWebviewIView.loadUrl(url);
    }
}
