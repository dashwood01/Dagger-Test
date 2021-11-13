package com.dashwood.daggertest.extra;

import android.app.Activity;
import android.app.Application;

import androidx.appcompat.app.AppCompatActivity;

import com.dashwood.daggertest.data.RepoServiceModule;
import com.dashwood.daggertest.di.ActivityInjector;
import com.dashwood.daggertest.networking.ServiceModule;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.BindsInstance;
import dagger.Component;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        ActivityBindingModule.class,
        ActivityInjectorModule.class,
        ServiceModule.class,
        RepoServiceModule.class,
})

public interface ApplicationComponent {
    void inject(A myApplication);
}
