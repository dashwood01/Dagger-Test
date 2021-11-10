package com.dashwood.daggertest.trending;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dashwood.daggertest.R;
import com.dashwood.daggertest.adapter.AdapterRecItemRepo;
import com.dashwood.daggertest.extra.BaseController;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;

public class TrendingReposController extends BaseController {

    @Inject
    TrendingReposPresenter presenter;
    @Inject
    TrendingReposViewModel viewModel;

    @BindView(R.id.recItemRepo)
    RecyclerView recItemRepo;
    @BindView(R.id.txtError)
    TextView txtError;
    @BindView(R.id.progressLoading)
    ProgressBar progressLoading;

    @Override
    protected void onViewBind(View view) {
        recItemRepo.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recItemRepo.setAdapter(new AdapterRecItemRepo(presenter));
    }

    @Override
    protected Disposable[] subscription() {
        return new Disposable[]{
                viewModel.loading()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(loading -> {
                    progressLoading.setVisibility(loading ? View.VISIBLE : View.GONE);
                    recItemRepo.setVisibility(loading ? View.GONE : View.VISIBLE);
                    txtError.setVisibility(loading ? View.GONE : txtError.getVisibility());
                }),
                viewModel.repos()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(((AdapterRecItemRepo) Objects.requireNonNull(recItemRepo.getAdapter()))::sendItems),
                viewModel.error()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(error -> {
                    if (error == -1) {
                        txtError.setVisibility(View.GONE);
                        txtError.setText("");
                        return;
                    }
                    txtError.setVisibility(View.VISIBLE);
                    recItemRepo.setVisibility(View.GONE);
                    txtError.setText(error);
                })
        };
    }

    @Override
    protected int layoutRes() {
        return R.layout.screen_trending_repos;
    }
}
