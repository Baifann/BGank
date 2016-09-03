package com.baifan.bgank.iview;

import com.baifan.bgank.entity.Meizhi;

import java.util.List;

/**
 * Created by baifan on 16/9/1.
 */
public interface MeizhiIView {

    void handleSuccessGetMeizhi(List<Meizhi> list);

    void initAdatper();

    void handleFailGetMeizhi(String s);

    void initRecyclerview();

    void onLoaded();

    void initEvents();
}
