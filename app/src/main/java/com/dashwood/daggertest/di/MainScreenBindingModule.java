package com.dashwood.daggertest.di;

import com.dashwood.daggertest.trending.TrendingReposComponent;
import com.dashwood.daggertest.trending.TrendingReposController;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        TrendingReposComponent.class,
})
public abstract class MainScreenBindingModule {
    @Binds
    @IntoMap
    @ClassKey(TrendingReposController.class)
    abstract AndroidInjector.Factory<?> bindingTrendingReposInjector(TrendingReposComponent.builder builder);

}
