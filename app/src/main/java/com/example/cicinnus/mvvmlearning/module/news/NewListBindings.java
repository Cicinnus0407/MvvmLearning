package com.example.cicinnus.mvvmlearning.module.news;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

/**
 * 绑定数据列表
 */

public class NewListBindings {
    @BindingAdapter({"items"})
    public static <T> void setItems(RecyclerView rv, List<NewsItemViewModel> list){
        ((NewsAdapter) rv.getAdapter()).setNewData(list);
    }
}
