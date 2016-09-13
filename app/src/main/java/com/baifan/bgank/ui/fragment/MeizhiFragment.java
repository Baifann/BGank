package com.baifan.bgank.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
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
public class MeizhiFragment extends Fragment implements MeizhiIView, MeizhiAdapter.OnChooseItemListener, SwipeRefreshLayout.OnRefreshListener {
    private MeizhiIpresenter mMeizhiInpresenter;

    @Bind(R.id.ry_meizhi)
    RecyclerView mRyMeizhi;

    @Bind(R.id.ly_refresh)
    SwipeRefreshLayout mLyRefresh;

    private MeizhiAdapter mAdapter;

    private GridLayoutManager mLayoutManager;

    private boolean isLoadMoreing;
    /**
     * 第几页
     */
    private int mPage = 1;

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
    }

    @Override
    public void handleSuccessGetMeizhi(List<Meizhi> list) {

        mAdapter.setList(list);
    }

    @Override
    public void initAdatper() {
        mAdapter = new MeizhiAdapter(getActivity());
        mAdapter.setOnChooseItemListener(this);
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
        mLayoutManager = new GridLayoutManager
                (getActivity(), 2);
        mRyMeizhi.setLayoutManager(mLayoutManager);
    }

    @Override
    public void initEvents() {
        mLyRefresh.setOnRefreshListener(this);
    }

    @Override
    public void loadRefresh() {
        mPage = 1;
        mLyRefresh.post(new Runnable() {
            @Override
            public void run() {
                mLyRefresh.setRefreshing(true);
                onRefresh();
            }
        });
    }

    @Override
    public void loadMore() {
        mPage++;
        mLyRefresh.post(new Runnable() {
            @Override
            public void run() {
                mLyRefresh.setRefreshing(true);
                onRefresh();
            }
        });
    }

    @Override
    public void onLoaded() {
        isLoadMoreing = false;
        mLyRefresh.setRefreshing(false);
    }

    @Override
    public void initScroll() {
        mRyMeizhi.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastitem = mLayoutManager.findLastVisibleItemPosition();
                if (dy > 0 && lastitem > mAdapter.getItemCount() - 5 && !isLoadMoreing) {
                    mMeizhiInpresenter.loadMore();
                    isLoadMoreing = true;
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        mMeizhiInpresenter.destroy();
        super.onDestroy();
    }

    @Override
    public void onItemClick(int position, View v) {
        mMeizhiInpresenter.onItemClick(position, v);
    }

    @Override
    public void onRefresh() {
        mMeizhiInpresenter.getMeizhiList("福利", "10", mPage + "");
    }
}
