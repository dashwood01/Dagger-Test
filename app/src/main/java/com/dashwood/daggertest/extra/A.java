package com.dashwood.daggertest.extra;

import android.app.Application;

import com.dashwood.daggertest.di.ActivityInjector;

import javax.inject.Inject;

public class A extends Application {

    @Inject
    ActivityInjector activityInjector;
    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        component.inject(this);


    }

    public ActivityInjector getActivityInjector() {
        return activityInjector;
    }
}
