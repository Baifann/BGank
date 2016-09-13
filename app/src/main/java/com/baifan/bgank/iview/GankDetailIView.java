package com.baifan.bgank.iview;

import com.baifan.bgank.entity.Gank;
import com.baifan.bgank.entity.GankTypeDetailList;

import java.util.List;

/**
 * Created by baifan on 16/9/2.
 */
public interface GankDetailIView {
    void handleSuccessGankDetail(GankTypeDetailList list);

    void handleFailGankDetail(String error);

    void onLoaded();

    void setMeizhiImage(String meizhiUrl);

    void initAdapter();

    void initEvents();
}
