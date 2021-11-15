package com.dashwood.daggertest.trending;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.dashwood.daggertest.R;
import com.dashwood.daggertest.adapter.AdapterRecItemRepo;
import com.dashwood.daggertest.databinding.ScreenTrendingReposBinding;
import com.dashwood.daggertest.extra.BaseController;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;

public class TrendingReposController extends BaseController {

    @Inject
    TrendingReposPresenter presenter;
    @Inject
    TrendingReposViewModel viewModel;

    private ScreenTrendingReposBinding binding;
    private AdapterRecItemRepo adapterRecItemRepo;

    @Override
    protected void onViewBind(View view) {
        /*
        binding = ScreenTrendingReposBinding.inflate(LayoutInflater.from(view.getContext()),
                (ViewGroup) view.getParent(), false);

         */
        if (view == null) {
            throw new IllegalArgumentException("View is null");
        }
        binding = ScreenTrendingReposBinding.bind(view);
        binding.recItemRepo.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapterRecItemRepo = new AdapterRecItemRepo(presenter);
        binding.recItemRepo.setAdapter(adapterRecItemRepo);
        Log.i("LOG", "Progress from on view bind");
    }

    @Override
    protected Disposable[] subscription() {
        return new Disposable[]{
                viewModel.loading()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(loading -> {
                    binding.progressLoading.setVisibility(loading ? View.VISIBLE : View.GONE);
                    binding.recItemRepo.setVisibility(loading ? View.GONE : View.VISIBLE);
                    binding.txtError.setVisibility(loading ? View.GONE : binding.txtError.getVisibility());
                }),
                viewModel.repos()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(list -> {
                    Log.i("LOG", "Size from response : " + list.size());
                    adapterRecItemRepo.sendItems(list);

                }),
                viewModel.error()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(error -> {
                    if (error == -1) {
                        binding.txtError.setVisibility(View.GONE);
                        binding.txtError.setText("");
                        return;
                    }
                    binding.txtError.setVisibility(View.VISIBLE);
                    binding.recItemRepo.setVisibility(View.GONE);
                    binding.txtError.setText(error);
                })
        }

                ;
    }

    @Override
    protected int layoutRes() {
        return R.layout.screen_trending_repos;
    }
}
