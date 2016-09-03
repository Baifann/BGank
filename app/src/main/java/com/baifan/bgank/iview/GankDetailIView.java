package com.baifan.bgank.iview;

import com.baifan.bgank.entity.Gank;

import java.util.List;

/**
 * Created by baifan on 16/9/2.
 */
public interface GankDetailIView {
    void handleSuccessGankDetail(List<Gank> list);

    void handleFailGankDetail(String error);

    void onLoaded();
}
