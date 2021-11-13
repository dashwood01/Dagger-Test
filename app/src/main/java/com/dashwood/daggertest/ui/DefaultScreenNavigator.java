package com.dashwood.daggertest.ui;

import android.util.Log;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.dashwood.daggertest.di.ActivityScope;

import javax.inject.Inject;

@ActivityScope
public class DefaultScreenNavigator implements ScreenNavigator {

    private Router router;

    @Inject
    DefaultScreenNavigator() {
        Log.i("LOG","Default screen navigator");
    }

    @Override
    public void initWithRouter(Router router, Controller rootScreen) {
        Log.i("LOG", "init with router");
        this.router = router;
        if (router.hasRootController()) {
            return;
        }
        router.setRoot(RouterTransaction.with(rootScreen));
    }

    @Override
    public boolean pop() {
        return router != null && router.handleBack();
    }

    @Override
    public void clear() {
        router = null;
    }
}
