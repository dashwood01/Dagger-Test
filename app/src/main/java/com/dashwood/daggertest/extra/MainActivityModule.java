package com.dashwood.daggertest.extra;

import com.dashwood.daggertest.MainActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import kotlin.Suppress;

@Suppress(names = "unused")
@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();

}
