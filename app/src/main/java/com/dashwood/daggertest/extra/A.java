package com.dashwood.daggertest.extra;

import android.app.Application;

import com.dashwood.daggertest.BuildConfig;
import com.dashwood.daggertest.di.ActivityInjector;
import com.dashwood.daggertest.di.ScreenInjector;
import com.dashwood.daggertest.ui.DefaultScreenNavigator;

import javax.inject.Inject;

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
