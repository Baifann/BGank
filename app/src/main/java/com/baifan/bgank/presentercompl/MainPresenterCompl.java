package com.baifan.bgank.presentercompl;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

import com.baifan.bgank.R;
import com.baifan.bgank.ipresenter.MainIpresenter;
import com.baifan.bgank.ui.fragment.MeizhiFragment;

/**
 * Created by baifan on 16/9/1.
 */
public class MainPresenterCompl implements MainIpresenter {

    public Context mContext;

    public MainPresenterCompl(Context context) {
        mContext = context;
    }

    @Override
    public void initFragment(FragmentManager fragmentManager) {
        MeizhiFragment meizhiFragment = MeizhiFragment.newInstance();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.ly_container, meizhiFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void destroy() {

    }
}
