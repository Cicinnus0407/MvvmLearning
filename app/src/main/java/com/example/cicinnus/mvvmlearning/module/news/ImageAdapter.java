package com.example.cicinnus.mvvmlearning.module.news;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by cicinnus on 17-8-23.
 */

public class ImageAdapter {

    @BindingAdapter(value = {"imgUrl"},requireAll = false)
    public static void setImageUrl(ImageView iv, String url){
        Glide.with(iv.getContext()).load(url)
                .into(iv);
    }
}
