package com.dashwood.daggertest.extra;

import android.app.Activity;

import com.dashwood.daggertest.MainActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        MainActivityComponent.class,
})
public abstract class ActivityBindingModule {
    @Binds
    @IntoMap
    @ClassKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> provideMainActivtyInjector(MainActivityComponent.Builder builder);
}
