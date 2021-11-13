package com.dashwood.daggertest.extra;

import android.app.Application;
import android.util.Log;

import com.dashwood.daggertest.BuildConfig;
import com.dashwood.daggertest.di.ActivityInjector;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.HasAndroidInjector;
import timber.log.Timber;

public class A extends Application {

    @Inject
    ActivityInjector activityInjector;
    ApplicationComponent component;


    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .activityInjectorModule(new ActivityInjectorModule())
                .build();
        component.inject(this);
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public ActivityInjector getActivityInjector() {
        return activityInjector;
    }
}
