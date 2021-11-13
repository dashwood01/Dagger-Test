package com.dashwood.daggertest.di;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.dashwood.daggertest.extra.A;
import com.dashwood.daggertest.extra.ActivityInjectorModule;
import com.dashwood.daggertest.extra.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.android.AndroidInjector;

public class ActivityInjector {

    private final Map<String, AndroidInjector<AppCompatActivity>> cache = new HashMap<>();
    private Map<Class<? extends AppCompatActivity>, Provider<AndroidInjector.Factory<? extends AppCompatActivity>>> activityInjectors;

    @Inject
    public ActivityInjector(Map<Class<? extends AppCompatActivity>,
            Provider<AndroidInjector.Factory<? extends AppCompatActivity>>> activityInjectors) {
        Log.i("LOG", "NEW CLASS RUN");
        if (activityInjectors == null) {
            Log.i("LOG", "AC INJS Null");
        }
        this.activityInjectors = activityInjectors;
    }

    @SuppressWarnings("ConstantConditions")
    public void inject(AppCompatActivity activity) {
        if (!(activity instanceof BaseActivity)) {
            throw new IllegalArgumentException("Activity must extends BaseActivity");
        }
        String instanceId = ((BaseActivity) activity).getInstanceId();
        if (cache.containsKey(instanceId)) {
            cache.get(instanceId).inject(activity);
            return;
        }
        //noinspection unchecked
        AndroidInjector.Factory<AppCompatActivity> injectorFactory =
                (AndroidInjector.Factory<AppCompatActivity>) activityInjectors.get(activity.getClass()).get();
        AndroidInjector<AppCompatActivity> injector = injectorFactory.create(activity);
        cache.put(instanceId, injector);
        injector.inject(activity);
    }

    void clear(AppCompatActivity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("Activity must extends BaseActivity");
        }
        cache.remove(((BaseActivity) activity).getInstanceId());
    }

    public static ActivityInjector get(AppCompatActivity context) {
        return ((A) (context.getApplication())).getActivityInjector();
    }

}

