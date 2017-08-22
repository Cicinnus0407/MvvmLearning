package com.example.cicinnus.mvvmlearning.app;

import android.app.Application;

import com.example.cicinnus.mvvmlearning.net.RetrofitClient;

import okhttp3.OkHttpClient;

/**
 * Created by cicinnus on 17-8-20.
 */

public class MvvmLearningApp extends Application {

    private static MvvmLearningApp app;

    public static MvvmLearningApp getAppContext() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        RetrofitClient.initClient_BaseUrl(new OkHttpClient(),"https://news-at.zhihu.com/");
    }
}
