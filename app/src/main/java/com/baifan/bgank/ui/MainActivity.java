package com.baifan.bgank.ui;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.baifan.bgank.R;
import com.baifan.bgank.ipresenter.MainIpresenter;
import com.baifan.bgank.presentercompl.MainPresenterCompl;

import butterknife.Bind;

public class MainActivity extends AppCompatActivity {
    private MainIpresenter mMainIpresenter;

    @Bind(R.id.tb)
    Toolbar mTb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initEvents();
        initDatas();
    }

    private void initEvents(){

    }

    private void initDatas(){
        mMainIpresenter = new MainPresenterCompl(this);
        mMainIpresenter.initFragment(getSupportFragmentManager());
    }
}
