package com.example.cicinnus.mvvmlearning.module.news;

import android.support.annotation.Nullable;

import com.example.cicinnus.mvvmlearning.R;
import com.example.cicinnus.mvvmlearning.base.BaseDataBindingAdapter;
import com.example.cicinnus.mvvmlearning.databinding.ItemNewsBinding;

import java.util.List;

/**
 * Created by cicinnus on 17-8-23.
 */

public class NewsAdapter extends BaseDataBindingAdapter<NewsItemViewModel,ItemNewsBinding> {


    public NewsAdapter(@Nullable List<NewsItemViewModel> data) {
        super(R.layout.item_news,data);
    }

    @Override
    protected void convert(ItemNewsBinding binding, NewsItemViewModel item) {

        binding.setItemViewModel(item);
    }



}
