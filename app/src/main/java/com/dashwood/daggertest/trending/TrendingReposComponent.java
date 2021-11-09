package com.dashwood.daggertest.trending;

import com.dashwood.daggertest.di.ScreenScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;

@ScreenScope
@Subcomponent
public interface TrendingReposComponent extends AndroidInjector<TrendingReposController> {
    @Subcomponent.Builder
    abstract class builder extends AndroidInjector.Builder<TrendingReposController>{
    }
}
