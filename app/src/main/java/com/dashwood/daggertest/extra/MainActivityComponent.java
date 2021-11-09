package com.dashwood.daggertest.extra;

import com.dashwood.daggertest.MainActivity;
import com.dashwood.daggertest.di.ActivityScope;
import com.dashwood.daggertest.di.MainScreenBindingModule;
import com.dashwood.daggertest.ui.NavigationModule;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;

@ActivityScope
@Subcomponent(modules = {
        MainScreenBindingModule.class,
        NavigationModule.class,
})
public interface MainActivityComponent extends AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity>{
        @Override
        public void seedInstance(MainActivity instance) {

        }
    }
}
