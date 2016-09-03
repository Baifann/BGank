package com.baifan.bgank.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.baifan.bgank.R;
import com.baifan.bgank.entity.Meizhi;
import com.baifan.bgank.ipresenter.MeizhiIpresenter;
import com.baifan.bgank.iview.MeizhiIView;
import com.baifan.bgank.presentercompl.MeizhiPeosenterCompl;
import com.baifan.bgank.ui.adapter.MeizhiAdapter;
import com.baifan.bgank.util.DLog;
import com.baifan.bgank.view.DividerGridItemDecoration;
import com.baifan.bgank.view.GridSpacingItemDecoration;
import com.baifan.bgank.view.MyItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by baifan on 16/9/1.
 */
public class MeizhiFragment extends Fragment implements MeizhiIView {
    private MeizhiIpresenter mMeizhiInpresenter;

    @Bind(R.id.ry_meizhi)
    RecyclerView mRyMeizhi;

    @Bind(R.id.ly_loading)
    LinearLayout mLyLoading;

    @Bind(R.id.ly_refresh)
    SwipeRefreshLayout mLyRefresh;

    private MeizhiAdapter mAdapter;

    public static MeizhiFragment newInstance() {
        MeizhiFragment meizhiFragment = new MeizhiFragment();
        return meizhiFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meizhi, null);
        ButterKnife.bind(this, view);
        initDatas();
        return view;
    }

    private void initDatas() {
        mMeizhiInpresenter = new MeizhiPeosenterCompl(this, getActivity());
        mMeizhiInpresenter.getMeizhiList("福利", "10", "1");
    }

    @Override
    public void handleSuccessGetMeizhi(List<Meizhi> list) {
        DLog.i("list:" + list.toString());
        mAdapter.setList(list);
    }

    @Override
    public void initAdatper() {

        mAdapter = new MeizhiAdapter(getActivity());
        mRyMeizhi.setAdapter(mAdapter);
    }

    @Override
    public void handleFailGetMeizhi(String s) {
        DLog.e("获取数据失败:" + s);
    }

    @Override
    public void initRecyclerview() {
        int spanCount = 2; // 3 columns
        int spacing = 20; // 50px
        boolean includeEdge = false;
        mRyMeizhi.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        GridLayoutManager staggeredGridLayoutManager = new GridLayoutManager
                (getActivity(), 2);
        mRyMeizhi.setLayoutManager(staggeredGridLayoutManager);
    }

    @Override
    public void onLoaded() {
        mLyLoading.setVisibility(View.GONE);
    }

    @Override
    public void initEvents() {

    }

    @Override
    public void onDestroy() {
        mMeizhiInpresenter.destroy();
        super.onDestroy();
    }
}
