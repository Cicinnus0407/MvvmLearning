package com.example.cicinnus.mvvmlearning.module.news;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

/**
 * Created by cicinnus on 17-8-27.
 */

public class User extends BaseObservable{

    public ObservableField<String> name = new ObservableField<>();
    public ObservableInt age = new ObservableInt();

    public User(String name,int age) {
        this.name.set(name);
        this.age.set(age);
    }


}
