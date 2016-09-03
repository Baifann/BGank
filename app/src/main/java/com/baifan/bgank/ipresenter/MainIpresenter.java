package com.baifan.bgank.ipresenter;

import android.support.v4.app.FragmentManager;

/**
 * Created by baifan on 16/9/1.
 */
public interface MainIpresenter {
    void initFragment(FragmentManager fragmentManager);

    void destroy();
}
