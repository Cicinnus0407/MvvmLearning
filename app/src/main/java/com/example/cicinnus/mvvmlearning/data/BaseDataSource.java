package com.example.cicinnus.mvvmlearning.data;

/**
 * 数据获取
 */

public interface BaseDataSource<T> {

    interface LoadDataCallBack<T>{

        void onDataLoaded(T t);

        void onDataLoadFail(Throwable t);

        void onDataLoadComplete();

    }

    void getData(LoadDataCallBack<T> callBack);
}
