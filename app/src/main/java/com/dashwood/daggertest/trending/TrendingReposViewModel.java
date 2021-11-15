package com.dashwood.daggertest.trending;

import androidx.annotation.NonNull;

import com.dashwood.daggertest.R;
import com.dashwood.daggertest.di.ScreenScope;
import com.dashwood.daggertest.model.Repo;
import com.jakewharton.rxrelay3.BehaviorRelay;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import timber.log.Timber;


@ScreenScope
class TrendingReposViewModel {

    private final BehaviorRelay<List<Repo>> repoRelay = BehaviorRelay.create();
    private final BehaviorRelay<Integer> errorRelay = BehaviorRelay.create();
    private final BehaviorRelay<Boolean> loadingRelay = BehaviorRelay.create();

    @Inject
    TrendingReposViewModel() {

    }

    Observable<Boolean> loading() {
        return loadingRelay;
    }

    Observable<List<Repo>> repos() {
        return repoRelay;
    }

    Observable<Integer> error() {
        return errorRelay;
    }

    Consumer<Boolean> loadingUpdated() {
        return loadingRelay;
    }

    @NonNull
    Consumer<List<Repo>> reposUpdated() {
        errorRelay.accept(-1);
        return repoRelay;
    }

    @NonNull
    Consumer<Throwable> errorUpdated() {
        return throwable -> {
            Timber.e(throwable, "error from repos");
            errorRelay.accept(R.string.error_repos);
        };
    }
}
