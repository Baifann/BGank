package com.baifan.bgank.util;

import java.util.List;

/**
 * Created by baifan on 16/9/12.
 */
public class ListUtil {

    public static boolean isEmpty(List<?> list) {
        if (list == null || list.size() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(List<?> list){
        return !isEmpty(list);
    }
}
