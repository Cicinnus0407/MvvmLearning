package com.example.cicinnus.mvvmlearning.module.news;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;

/**
 * Created by cicinnus on 17-8-22.
 */

public class NewsItemViewModel extends BaseObservable{

    private Context mContext;

    //数据集
    public NewsBean.StoriesBean storiesBean;


    //标题
    public ObservableField<String> title = new ObservableField<>();
    //图片
    public ObservableField<String> imgUrl = new ObservableField<>();


    public NewsItemViewModel(Context context, NewsBean.StoriesBean storiesBean) {
        this.mContext = context;
        this.storiesBean = storiesBean;
        title.set(storiesBean.getTitle());
        imgUrl.set(storiesBean.getImages().get(0));

    }
}
