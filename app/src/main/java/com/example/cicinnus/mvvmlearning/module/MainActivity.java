package com.example.cicinnus.mvvmlearning.module;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.cicinnus.mvvmlearning.R;
import com.example.cicinnus.mvvmlearning.data.remote.NewsRepository;
import com.example.cicinnus.mvvmlearning.module.news.NewsFragment;
import com.example.cicinnus.mvvmlearning.module.news.NewsViewModel;
import com.example.cicinnus.mvvmlearning.utils.ViewModelHolder;

public class MainActivity extends AppCompatActivity {

    private static final String NEWS_VIEW_MODEL_TAG = "news_view_model_tag";
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpToolbar();

        setUpDrawer();

        NewsFragment newsFragment = findOrCreateViewFragment();

        NewsViewModel newsViewModel = findOrCreateViewModel();

    }

    private NewsViewModel findOrCreateViewModel() {
        @SuppressWarnings("unchecked")
        ViewModelHolder<NewsViewModel> retainedViewModel =
                (ViewModelHolder<NewsViewModel>) getSupportFragmentManager()
                        .findFragmentByTag(NEWS_VIEW_MODEL_TAG);

        if (retainedViewModel != null && retainedViewModel.getViewmodel() != null) {
            // If the model was retained, return it.
            return retainedViewModel.getViewmodel();
        } else {
            // There is no ViewModel yet, create it.
            NewsViewModel viewModel = new NewsViewModel(getApplicationContext(),new NewsRepository());
            // and bind it to this Activity's lifecycle using the Fragment Manager.
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(ViewModelHolder.createContainer(viewModel), NEWS_VIEW_MODEL_TAG);
            transaction.commit();
            return viewModel;
        }
    }

    private NewsFragment findOrCreateViewFragment() {
        NewsFragment newsFragment = (NewsFragment) getSupportFragmentManager().findFragmentById(R.id.fl_content);

        if (newsFragment == null) {
            //添加Fragment到FrameLayout
            newsFragment = NewsFragment.newInstance();
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fl_content, newsFragment);
            transaction.commit();
        }
        return newsFragment;
    }

    private void setUpDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                }
                // Close the navigation drawer when an item is selected.
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private void setUpToolbar() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("MVVMLearning");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


}
