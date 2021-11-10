package com.dashwood.daggertest.extra;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;
import com.dashwood.daggertest.di.Injector;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public abstract class BaseController extends Controller {

    private CompositeDisposable disposable = new CompositeDisposable();
    private Unbinder unbinder;
    private boolean isInjected = false;

    @Override
    protected void onContextAvailable(@NonNull Context context) {
        if (!isInjected) {
            Injector.inject(this);
            isInjected = true;
        }
        super.onContextAvailable(context);
    }

    @NonNull
    @Override
    protected final View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @Nullable Bundle savedViewState) {
        View view = inflater.inflate(layoutRes(), container);
        unbinder = ButterKnife.bind(view);
        onViewBind(view);
        disposable.addAll(subscription());
        return null;
    }

    protected void onViewBind(View view) {

    }

    @Override
    protected void onDestroyView(@NonNull View view) {
        disposable.clear();
        if (unbinder == null) {
            return;
        }
        unbinder.unbind();
        unbinder = null;
    }

    protected Disposable[] subscription() {
        return new Disposable[0];
    }

    @LayoutRes
    protected abstract int layoutRes();
}
