package com.dashwood.daggertest.di;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.bluelinelabs.conductor.Controller;

public class Injector {
    private Injector() {

    }

    public static void inject(AppCompatActivity appCompatActivity) {
        ActivityInjector.get(appCompatActivity).inject(appCompatActivity);
    }

    public static void clearComponent(AppCompatActivity activity) {
        ActivityInjector.get(activity).clear(activity);
    }

    public static void inject(Controller controller) {
        Log.i("LOG", "Inject controller");
        ScreenInjector.get(controller.getActivity()).inject(controller);
    }

    public static void clearComponent(Controller controller) {
        ScreenInjector.get(controller.getActivity()).clear(controller);
    }
}
