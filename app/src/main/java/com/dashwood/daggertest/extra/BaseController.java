package com.dashwood.daggertest.extra;

import android.content.Context;

import com.bluelinelabs.conductor.Controller;
import com.dashwood.daggertest.di.Injector;

import androidx.annotation.NonNull;

public abstract class BaseController extends Controller {

    private boolean isInjected = false;

    @Override
    protected void onContextAvailable(@NonNull Context context) {
        if (!isInjected) {
            Injector.inject(this);
            isInjected = true;
        }
        super.onContextAvailable(context);

    }
}
