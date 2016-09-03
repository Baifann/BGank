package com.baifan.bgank.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by baifan on 16/9/1.
 */
public class ImageLoaderUtil {
    public static void loadImage(String url, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(url)
                .centerCrop()
                .into(imageView);
    }
}
