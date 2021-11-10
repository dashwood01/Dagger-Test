package com.dashwood.daggertest.extra;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dashwood.daggertest.BuildConfig;
import com.dashwood.daggertest.di.ActivityInjector;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import timber.log.Timber;

public class A extends Application {

    @Inject
    ActivityInjector activityInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("LOG", "APPLICATION A");
        ApplicationComponent component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        component.inject(this);
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public ActivityInjector getActivityInjector() {
        if (activityInjector == null) {
            throw new IllegalArgumentException("activityInjector is null");
        }
        return activityInjector;
    }
}
