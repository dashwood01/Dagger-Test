package com.dashwood.daggertest.extra;

import android.app.Activity;
import android.app.Application;

import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        ActivityBindingModule.class,
})
public interface ApplicationComponent{
    void inject(Application myApplication);
}
