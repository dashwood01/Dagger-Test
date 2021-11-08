package com.dashwood.daggertest.di;

import com.bluelinelabs.conductor.Controller;
import com.dashwood.daggertest.trending.TrendingReposComponent;
import com.dashwood.daggertest.trending.TrendingReposController;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        TrendingReposComponent.class,
})
public abstract class MainScreenBindingModule {
    @Binds
    @IntoMap
    @ControllerKey(TrendingReposController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindingTrendingReposInjector(TrendingReposComponent.builder builder);

}
