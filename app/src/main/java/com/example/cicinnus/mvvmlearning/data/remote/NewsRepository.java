package com.example.cicinnus.mvvmlearning.data.remote;

import com.example.cicinnus.mvvmlearning.app.MvvmLearningApp;
import com.example.cicinnus.mvvmlearning.data.BaseDataSource;
import com.example.cicinnus.mvvmlearning.module.news.NewsBean;
import com.example.cicinnus.mvvmlearning.module.news.NewsService;
import com.example.cicinnus.mvvmlearning.net.RetrofitClient;
import com.example.cicinnus.mvvmlearning.utils.NetWorkUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by cicinnus on 17-8-22.
 */

public class NewsRepository implements BaseDataSource<NewsBean> {





    @SuppressWarnings("unchecked")
    @Override
    public void getData(final LoadDataCallBack<NewsBean> callBack) {

        RetrofitClient.getInstance()
                .create(NewsService.class)
                .getNewsBean()
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        //获取缓存
                        //如果没网断开
                        if(!NetWorkUtil.isNetworkConnected(MvvmLearningApp.getAppContext())){
                            disposable.dispose();
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NewsBean>() {
                    @Override
                    public void accept(NewsBean newsBean) throws Exception {
                        callBack.onDataLoaded(newsBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callBack.onDataLoadFail(throwable);
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        callBack.onDataLoadComplete();
                    }
                });
    }
}
