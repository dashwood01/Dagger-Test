package com.dashwood.daggertest.extra;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

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
    abstract AndroidInjector.Factory<?> provideMainActivtyInjector(MainActivityComponent.Builder builder);
}
