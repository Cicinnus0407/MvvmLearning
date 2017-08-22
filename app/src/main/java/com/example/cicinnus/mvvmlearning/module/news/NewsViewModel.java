package com.example.cicinnus.mvvmlearning.module.news;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;

import com.example.cicinnus.mvvmlearning.data.BaseDataSource;
import com.example.cicinnus.mvvmlearning.data.remote.NewsRepository;

/**
 * 日报列表的ViewModel
 */

public class NewsViewModel extends BaseObservable {


    //数据Bean
    private ObservableList<NewsBean> newsBeen = new ObservableArrayList<>();

    //数据是否加载中
    private ObservableBoolean dataLoading = new ObservableBoolean(false);


    private Context mContext;
    private NewsRepository repository;

    public NewsViewModel(Context mContext, NewsRepository repository) {
        this.mContext = mContext.getApplicationContext();
        this.repository = repository;
    }


    public void loadNews(boolean showLoadingUI) {
        if (showLoadingUI) {
            dataLoading.set(true);
        }

        repository.getData(new BaseDataSource.LoadDataCallBack<NewsBean>() {
            @Override
            public void onDataLoaded(NewsBean newsBean) {
                newsBeen.add(newsBean);
            }

            @Override
            public void onDataLoadFail(Throwable t) {
                dataLoading.set(false);
            }

            @Override
            public void onDataLoadComplete() {
                dataLoading.set(false);

            }
        });
    }
}
