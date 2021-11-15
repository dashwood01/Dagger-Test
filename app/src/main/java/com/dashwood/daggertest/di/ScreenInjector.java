package com.dashwood.daggertest.di;

import android.app.Activity;
import android.util.Log;

import com.bluelinelabs.conductor.Controller;
import com.dashwood.daggertest.extra.BaseActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.android.AndroidInjector;

@ActivityScope
public class ScreenInjector {

    private final Map<String, AndroidInjector<Controller>> cache = new HashMap<>();
    private final Map<Class<?>, Provider<AndroidInjector.Factory<?>>> screenInjector;

    @Inject
    ScreenInjector(Map<Class<?>, Provider<AndroidInjector.Factory<?>>> screenInjector) {
        Log.i("LOG", "Screen injector inject is run");
        this.screenInjector = screenInjector;
    }

    void inject(Controller controller) {
        if (controller == null) {
            throw new IllegalArgumentException("Controller must extends BaseController");
        }
        String instanceId = controller.getInstanceId();
        if (cache.containsKey(instanceId)) {
            Objects.requireNonNull(cache.get(instanceId)).inject(controller);
            return;
        }
        Log.i("LOG", "B");
        if (screenInjector == null) {
            throw new IllegalArgumentException("Screen injector is null");
        }

        for (Map.Entry<Class<?>, Provider<AndroidInjector.Factory<?>>> entry : screenInjector.entrySet()) {
            Log.i("LOG", "KEY : " + entry.getKey() + " Value : " + entry.getValue());
        }
        Log.i("LOG", "screen injector size : " + screenInjector.size());
        if (screenInjector.get(controller.getClass()) == null) {
            throw new IllegalArgumentException("Provider is null");
        }
        //noinspection unchecked
        AndroidInjector.Factory<Controller> injectFactory =
                (AndroidInjector.Factory<Controller>) screenInjector.get(controller.getClass()).get();
        AndroidInjector<Controller> injector = injectFactory.create(controller);
        cache.put(instanceId, injector);
        injector.inject(controller);
    }

    void clear(Controller controller) {
        if (controller == null) {
            throw new IllegalArgumentException("Controller must extends BaseController");
        }
        cache.remove(controller.getInstanceId());
    }

    static ScreenInjector get(Activity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("Controller must be hosted BaseActivity");
        }
        return ((BaseActivity) activity).getScreenInjector();
    }
}
