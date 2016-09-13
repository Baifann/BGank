package com.baifan.bgank.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.baifan.bgank.R;
import com.baifan.bgank.ipresenter.GankWebviewIpresenter;
import com.baifan.bgank.iview.GankWebviewIView;
import com.baifan.bgank.presentercompl.GankWebviewIPresenterCompl;
import com.baifan.bgank.util.DLog;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by baifan on 16/9/13.
 */
public class GankWebviewActivity extends AppCompatActivity implements GankWebviewIView {
    @Bind(R.id.wv_gank)
    WebView mWvGank;

    @Bind(R.id.pb_gank)
    ProgressBar mPbGank;

    private GankWebviewIpresenter mGankIpresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gank_webview);
        ButterKnife.bind(this);
        initDatas();
    }

    private void initDatas() {
        mGankIpresenter = new GankWebviewIPresenterCompl(this);
        mGankIpresenter.getDataFromIntent(getIntent());
    }

    @Override
    public void initWebview() {
        WebSettings webSettings = mWvGank.getSettings();
        // 支持获取手势焦点，输入用户名、密码或其他
        mWvGank.requestFocusFromTouch();
        webSettings.setJavaScriptEnabled(true); // 支持js
        // webSettings.setPluginsEnabled(true); // 支持插件
        // webSettings.setRenderPriority(RenderPriority.HIGH); // 提高渲染的优先级
        // 设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); // 将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
    }

    @Override
    public void loadUrl(String url) {
        mWvGank.loadUrl(url);
    }

    @Override
    public void setUpWebviewProgress() {
        mWvGank.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onReceivedTitle(WebView view, String title) {

                super.onReceivedTitle(view, title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    mPbGank.setVisibility(View.GONE);
                } else {
                    if (View.INVISIBLE == mPbGank.getVisibility()) {
                        mPbGank.setVisibility(View.VISIBLE);
                    }
                    mPbGank.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }

        });
    }
}
