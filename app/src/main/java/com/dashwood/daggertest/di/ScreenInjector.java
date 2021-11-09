package com.dashwood.daggertest.di;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import com.bluelinelabs.conductor.Controller;
import com.dashwood.daggertest.extra.BaseActivity;
import com.dashwood.daggertest.extra.BaseController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;

@ActivityScope
public class ScreenInjector {

    private final Map<Class<? extends Controller>, Provider<AndroidInjector.Factory<? extends Controller>>> screenInjector;
    private final Map<String, AndroidInjector<Controller>> cache = new HashMap<>();

    @Inject
    ScreenInjector(Map<Class<? extends Controller>, Provider<AndroidInjector.Factory<? extends Controller>>> screenInjector) {
        this.screenInjector = screenInjector;
    }

    void inject(Controller controller) {
        if (!(controller instanceof BaseController)) {
            throw new IllegalArgumentException("Controller must extends BaseController");
        }
        String instanceId = controller.getInstanceId();
        if (cache.containsKey(instanceId)) {
            Objects.requireNonNull(cache.get(instanceId)).inject(controller);
            return;
        }
        //noinspection unchecked
        AndroidInjector.Factory<Controller> injectFactory =
                (AndroidInjector.Factory<Controller>) Objects.requireNonNull(screenInjector.get(controller.getClass())).get();
        AndroidInjector<Controller> injector = injectFactory.create(controller);
        cache.put(instanceId, injector);
        injector.inject(controller);
    }

    void clear(Controller controller) {
        if (!(controller instanceof BaseController)) {
            throw new IllegalArgumentException("Controller must extends BaseController");
        }
        cache.remove(controller.getInstanceId());
    }

    static ScreenInjector get(Activity activity) {
        if (!(activity instanceof BaseActivity)) {
            throw new IllegalArgumentException("Controller must be hosted BaseActivity");
        }

        return ((BaseActivity) activity).getScreenInjector();
    }
}
