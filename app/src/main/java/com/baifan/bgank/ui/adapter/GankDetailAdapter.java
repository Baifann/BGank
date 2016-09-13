package com.baifan.bgank.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baifan.bgank.R;
import com.baifan.bgank.entity.Gank;
import com.baifan.bgank.util.DLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by baifan on 16/9/12.
 */
public class GankDetailAdapter extends BaseAdapter {

    private List<Gank> mList = new ArrayList<>();

    private OnSelectListener mListener;

    public void setList(List<Gank> list) {
        mList = list;
        this.notifyDataSetChanged();
    }

    public interface OnSelectListener{
        void onSelect(String url);
    }

    public void setOnSelectListener(OnSelectListener listener){
        mListener = listener;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyHolder vh = null;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_gank_detail, null);
            vh = new MyHolder(view);
            view.setTag(vh);
        } else {
            vh = (MyHolder) view.getTag();
        }
        vh.mPosition = i;
        fillData(mList.get(i), vh);
        return view;
    }

    private void fillClickEvent(final Gank gank, MyHolder vh){
        vh.mLyTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener != null){
                    mListener.onSelect(gank.getUrl());
                }
            }
        });
    }

    private void fillData(Gank gank, MyHolder vh) {
        String type = gank.getType();
        if (0 == vh.mPosition) {
            vh.mTvTab.setText(type);
            TextPaint tp = vh.mTvTab.getPaint();
            tp.setFakeBoldText(true);
            vh.mTvTab.setVisibility(View.VISIBLE);
        } else {
            vh.mTvTab.setVisibility(View.GONE);
        }
        vh.mTvTitle.setText(addWho(gank));
        fillClickEvent(gank, vh);
    }

    private String addWho(Gank gank) {
        StringBuilder sb = new StringBuilder(gank.getDesc());
        sb.append("(")
                .append("via:")
                .append(gank.getWho())
                .append(")");
        return sb.toString();
    }

    public class MyHolder {
        @Bind(R.id.tv_tab)
        TextView mTvTab;

        @Bind(R.id.tv_title)
        TextView mTvTitle;

        @Bind(R.id.ly_title)
        LinearLayout mLyTitle;

        int mPosition;

        public MyHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }
}
