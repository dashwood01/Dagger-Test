package com.dashwood.daggertest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import com.bluelinelabs.conductor.Controller;
import com.dashwood.daggertest.di.ScreenInjector;
import com.dashwood.daggertest.extra.BaseActivity;
import com.dashwood.daggertest.trending.TrendingReposController;

public class MainActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        Log.i("LOG", "Main activity layout res");
        return R.layout.activity_main;
    }

    @Override
    protected Controller initialScreen() {
        Log.i("LOG", "Main activity Controller");
        return new TrendingReposController();
    }


}