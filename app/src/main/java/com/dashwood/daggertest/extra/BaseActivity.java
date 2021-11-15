package com.dashwood.daggertest.extra;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.Router;
import com.dashwood.daggertest.R;
import com.dashwood.daggertest.di.Injector;
import com.dashwood.daggertest.di.ScreenInjector;
import com.dashwood.daggertest.ui.ScreenNavigator;

import java.util.UUID;

import javax.inject.Inject;

public abstract class BaseActivity extends AppCompatActivity {
    @Inject
    ScreenInjector screenInjector;
    @Inject
    ScreenNavigator screenNavigator;

    private static final String INSTANCE_ID_KEY = "instance_id";
    private String instanceId;
    private Router router;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            instanceId = savedInstanceState.getString(INSTANCE_ID_KEY);
        } else {
            instanceId = UUID.randomUUID().toString();
        }
        Injector.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(layoutRes());
        ViewGroup screenContainer = findViewById(R.id.screen_container);
        if (screenContainer == null) {
            throw new IllegalArgumentException("Activity must have a view name 'screen_container'");
        }
        router = Conductor.attachRouter(this, screenContainer, savedInstanceState);
        //screenNavigator = ((A) getApplication()).getDefaultScreenNavigator();
        if (screenNavigator == null) {
            throw new IllegalArgumentException("screen nav is null");
        }
        screenNavigator.initWithRouter(router, initialScreen());
        Log.i("LOG", "A in base activity");
        monitorBackStack();
        // super.onBackPressed();
    }

    @LayoutRes
    protected abstract int layoutRes();

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(INSTANCE_ID_KEY, instanceId);
    }

    public String getInstanceId() {
        return instanceId;
    }

    protected abstract Controller initialScreen();

    public ScreenInjector getScreenInjector() {
        return screenInjector;
    }

    @Override
    public void onBackPressed() {
        if (!screenNavigator.pop()) super.onBackPressed();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        screenNavigator.clear();
        if (isFinishing()) {
            Injector.clearComponent(this);
        }
    }

    private void monitorBackStack() {
        router.addChangeListener(new ControllerChangeHandler.ControllerChangeListener() {
            @Override
            public void onChangeStarted(@Nullable Controller to, @Nullable Controller from, boolean isPush, @NonNull ViewGroup container, @NonNull ControllerChangeHandler handler) {

            }

            @Override
            public void onChangeCompleted(@Nullable Controller to, @Nullable Controller from, boolean isPush, @NonNull ViewGroup container, @NonNull ControllerChangeHandler handler) {
                if (!isPush && from != null) {
                    Injector.clearComponent(from);
                }
            }
        });
    }
}
