package com.dashwood.daggertest.extra;

import com.dashwood.daggertest.data.RepoServiceModule;
import com.dashwood.daggertest.networking.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        ActivityBindingModule.class,
        ServiceModule.class,
        RepoServiceModule.class,
})

public interface ApplicationComponent {
    void inject(A myApplication);
}
