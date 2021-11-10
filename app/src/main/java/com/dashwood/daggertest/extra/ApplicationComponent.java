package com.dashwood.daggertest.extra;

import android.app.Activity;
import android.app.Application;

import androidx.appcompat.app.AppCompatActivity;

import com.dashwood.daggertest.data.RepoServiceModule;
import com.dashwood.daggertest.networking.ServiceModule;

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
        ServiceModule.class,
        RepoServiceModule.class,
})
public interface ApplicationComponent {
    void inject(Application myApplication);
}
