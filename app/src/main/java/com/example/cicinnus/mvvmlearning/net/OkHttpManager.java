package com.example.cicinnus.mvvmlearning.net;


import com.example.cicinnus.mvvmlearning.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpManager {
    private static OkHttpClient okHttpClient;

    public static OkHttpClient getInstance() {
        if (okHttpClient == null) {
            synchronized (OkHttpManager.class) {
                if (okHttpClient == null) {

                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                        builder.addInterceptor(interceptor);
                    }
                    //超时时间
                    builder.connectTimeout(15, TimeUnit.SECONDS);//15S连接超时
                    builder.readTimeout(15, TimeUnit.SECONDS);//15s读取超时
                    builder.writeTimeout(15, TimeUnit.SECONDS);//15s写入超时
                    //取消错误重连
                    builder.retryOnConnectionFailure(false);
                    okHttpClient = builder.build();
                }
            }
        }
        return okHttpClient;
    }
}
