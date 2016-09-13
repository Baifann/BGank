package com.baifan.bgank.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by baifan on 16/9/12.
 */
public class LoadmoreRecyclerView extends RecyclerView{
    /**
     * 是否正在加载
     */
    private boolean isLoadMoreing;

    private interface OnLoadmoreListener {
        void onLoadmore();
    }

    private OnLoadmoreListener mOnLoadmoreListener;

    public void setOnLoadmoreListener(OnLoadmoreListener onLoadmoreListener) {
        mOnLoadmoreListener = onLoadmoreListener;
    }

    public LoadmoreRecyclerView(Context context) {
        this(context, null);
    }

    public LoadmoreRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadmoreRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    public boolean isLoadMoreing(){
        return isLoadMoreing;
    }

    public void setLoadMore(boolean isLoadMoreing){
        this.isLoadMoreing = isLoadMoreing;
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
    }
}
