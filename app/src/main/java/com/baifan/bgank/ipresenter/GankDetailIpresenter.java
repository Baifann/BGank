package com.baifan.bgank.ipresenter;

import android.content.Intent;

/**
 * Created by baifan on 16/9/2.
 */
public interface GankDetailIpresenter {
    void destroy();

    void getDataFromIntent(Intent intent);

    void goToWebViewActivity(String url);
}
