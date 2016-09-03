package com.baifan.bgank.ipresenter;

import com.baifan.bgank.entity.MeizhiList;
import com.baifan.bgank.ui.adapter.MeizhiAdapter;

/**
 * Created by baifan on 16/9/1.
 */
public interface MeizhiIpresenter {

    MeizhiList getMeizhiList(String type, String pageSize, String page);

    void destroy();
}
