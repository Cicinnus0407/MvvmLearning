package com.example.cicinnus.mvvmlearning.module.news;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.cicinnus.mvvmlearning.BR;
import com.example.cicinnus.mvvmlearning.R;

/**
 * Created by cicinnus on 17-8-23.
 */

public class NewsAdapter extends BaseQuickAdapter<NewsItemViewModel,NewsAdapter.NewsViewHolder> {


    public NewsAdapter() {
        super(R.layout.item_news,null);
    }


    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        ViewDataBinding binding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false);
        if (binding == null) {
            return super.getItemView(layoutResId, parent);
        }
        View view = binding.getRoot();
        view.setTag(R.id.BaseQuickAdapter_databinding_support, binding);
        return view;
    }

    @Override
    protected void convert(NewsViewHolder helper, NewsItemViewModel item) {
        ViewDataBinding binding = helper.getBinding();
        binding.setVariable(BR.itemViewModel, item);
        binding.executePendingBindings();
    }


    public static class NewsViewHolder extends BaseViewHolder {

        public NewsViewHolder(View view) {
            super(view);
        }

        public ViewDataBinding getBinding() {
            return (ViewDataBinding) itemView.getTag(R.id.BaseQuickAdapter_databinding_support);
        }
    }

}
