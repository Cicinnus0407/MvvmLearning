package com.example.cicinnus.mvvmlearning.module.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cicinnus.mvvmlearning.app.MvvmLearningApp;
import com.example.cicinnus.mvvmlearning.data.remote.NewsRepository;
import com.example.cicinnus.mvvmlearning.databinding.FragmentNewsBinding;

/**
 * Created by cicinnus on 17-8-22.
 */

public class NewsFragment extends Fragment {


    private NewsViewModel newsViewModel;
    private FragmentNewsBinding fragmentNewsBinding;
    private User user;


    public static NewsFragment newInstance() {

        return new NewsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentNewsBinding = FragmentNewsBinding.inflate(inflater, container, false);
        newsViewModel = new NewsViewModel(MvvmLearningApp.getAppContext(), new NewsRepository());
        fragmentNewsBinding.setViewModel(newsViewModel);
        user = new User("什么辣鸡", 12);
//        fragmentNewsBinding.setUser(user);
        newsViewModel.start();

        setUpListAdapter();

        return fragmentNewsBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void setUpListAdapter() {
        RecyclerView rvNews = fragmentNewsBinding.rvNews;
        rvNews.setLayoutManager(new LinearLayoutManager(MvvmLearningApp.getAppContext()));
        NewsAdapter newsAdapter = new NewsAdapter(newsViewModel.itemViewModels);
        rvNews.setAdapter(newsAdapter);
    }

    public void setNewsViewModel(NewsViewModel newsViewModel) {
        this.newsViewModel = newsViewModel;
    }


}
