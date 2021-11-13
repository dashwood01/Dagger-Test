package com.dashwood.daggertest.ui;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;

import dagger.Provides;


public interface ScreenNavigator {
    void initWithRouter(Router router, Controller rootScreen);
    boolean pop();
    void clear();
}
