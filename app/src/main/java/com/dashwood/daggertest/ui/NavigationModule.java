package com.dashwood.daggertest.ui;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class NavigationModule {
    @Binds
    abstract ScreenNavigator provideScreenNavigator(DefaultScreenNavigator screenNavigator);
}
