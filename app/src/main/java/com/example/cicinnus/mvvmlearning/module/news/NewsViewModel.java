package com.example.cicinnus.mvvmlearning.module.news;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;

import com.example.cicinnus.mvvmlearning.data.BaseDataSource;
import com.example.cicinnus.mvvmlearning.data.remote.NewsRepository;
import com.example.cicinnus.mvvmlearning.net.SchedulersCompact;
import com.orhanobut.logger.Logger;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * 日报列表的ViewModel
 */

public class NewsViewModel {


    //数据和view交互的viewModel
    public final ObservableArrayList<NewsItemViewModel> itemViewModels = new ObservableArrayList<>();

    //数据是否加载中
    private ObservableBoolean dataLoading = new ObservableBoolean(false);


    private Context mContext;
    private NewsRepository repository;

    public NewsViewModel(Context mContext, NewsRepository repository) {
        this.mContext = mContext.getApplicationContext();
        this.repository = repository;
    }

    public void start(){
        loadNews(true);
    }


    public void loadNews(boolean showLoadingUI) {
        if (showLoadingUI) {
            dataLoading.set(true);
        }

        repository.getData(new BaseDataSource.LoadDataCallBack<NewsBean>() {
            @Override
            public void onDataLoaded(NewsBean newsBean) {
                Observable.fromIterable(newsBean.getStories())
                        .compose(SchedulersCompact.<NewsBean.StoriesBean>applyIoSchedulers())
                        .subscribe(new Consumer<NewsBean.StoriesBean>() {
                            @Override
                            public void accept(NewsBean.StoriesBean storiesBean) throws Exception {
                                itemViewModels.add(new NewsItemViewModel(mContext,storiesBean));
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Logger.e(throwable.getMessage());
                            }
                        });
            }

            @Override
            public void onDataLoadFail(Throwable t) {
                dataLoading.set(false);
                Logger.e(t.getMessage());
            }

            @Override
            public void onDataLoadComplete() {
                dataLoading.set(false);

            }
        });
    }
}
