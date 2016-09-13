package com.baifan.bgank.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baifan.bgank.R;
import com.baifan.bgank.entity.Meizhi;
import com.baifan.bgank.util.ImageLoaderUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by baifan on 16/9/1.
 */
public class MeizhiAdapter extends RecyclerView.Adapter<MeizhiAdapter.MyHolder> {
    private Context mContext;
    private List<Meizhi> mList = new ArrayList<>();

    private List<Integer> mHeightList = new ArrayList<>();

    public interface OnChooseItemListener {
        void onItemClick(int position, View v);
    }

    private OnChooseItemListener mListener;

    public void setOnChooseItemListener(OnChooseItemListener listener) {
        mListener = listener;
    }

    public MeizhiAdapter(Context context) {
        mContext = context;
    }

    public void setList(List<Meizhi> list) {
        mList = list;
        getRandomHeight(list);
        this.notifyDataSetChanged();
    }

    private void getRandomHeight(List<Meizhi> mList) {
        mHeightList = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            //随机的获取一个范围为200-600直接的高度
            mHeightList.add((int) (300 + Math.random() * 400));
        }
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_meizhi, null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.position = position;
        Meizhi meizhi = mList.get(position);
//        ViewGroup.LayoutParams layoutParams = holder.mImgMeizhi.getLayoutParams();
//        layoutParams.height = mHeightList.get(position);
//        holder.mImgMeizhi.setLayoutParams(layoutParams);

        fillData(meizhi, holder);
        handleClick(holder);
    }

    private void fillData(Meizhi meizhi, MyHolder holder) {
        holder.mTvTitle.setText(meizhi.getDesc());
        ImageLoaderUtil.loadImage(meizhi.getUrl(), holder.mImgMeizhi);
    }

    private void handleClick(final MyHolder holder) {
        holder.mImgMeizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onItemClick(holder.position, holder.mImgMeizhi);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.img_meizhi)
        ImageView mImgMeizhi;

        @Bind(R.id.tv_title)
        TextView mTvTitle;

        int position;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
