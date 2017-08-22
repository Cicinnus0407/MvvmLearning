package com.example.cicinnus.mvvmlearning.module.news;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by cicinnus on 17-8-22.
 */

public interface NewsService {

    @GET("api/4/news/latest")
    Observable<NewsBean> getNewsBean();
}
