package com.dashwood.daggertest.extra;

import android.app.Activity;
import android.app.Application;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        ActivityBindingModule.class,
})
public interface ApplicationComponent {
    void inject(Application myApplication);
}
