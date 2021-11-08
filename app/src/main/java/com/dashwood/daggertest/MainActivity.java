package com.dashwood.daggertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bluelinelabs.conductor.Controller;
import com.dashwood.daggertest.extra.BaseActivity;
import com.dashwood.daggertest.trending.TrendingReposController;

public class MainActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected Controller initialScreen() {
        return new TrendingReposController();
    }
}