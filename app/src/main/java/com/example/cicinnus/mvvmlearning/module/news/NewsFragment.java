package com.example.cicinnus.mvvmlearning.module.news;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cicinnus.mvvmlearning.R;
import com.example.cicinnus.mvvmlearning.app.MvvmLearningApp;
import com.example.cicinnus.mvvmlearning.databinding.FragmentNewsBinding;

/**
 * Created by cicinnus on 17-8-22.
 */

public class NewsFragment extends Fragment {


    private NewsViewModel newsViewModel;
    private FragmentNewsBinding fragmentNewsBinding;
    private NewsAdapter newsAdapter;


    public static NewsFragment newInstance() {

        return new NewsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentNewsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_news,container,false);
//        newsViewModel = new NewsViewModel(MvvmLearningApp.getAppContext(), new NewsRepository());
        newsViewModel.start();
        fragmentNewsBinding.setViewModel(newsViewModel);

        return fragmentNewsBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpListAdapter();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                newsAdapter.getData();
            }
        },2000);


    }

    private void setUpListAdapter() {
        RecyclerView rvNews = fragmentNewsBinding.rvNews;
        rvNews.setLayoutManager(new LinearLayoutManager(MvvmLearningApp.getAppContext()));
        newsAdapter = new NewsAdapter();
        rvNews.setAdapter(newsAdapter);
    }

    public void setNewsViewModel(NewsViewModel newsViewModel) {
        this.newsViewModel = newsViewModel;
    }


}
