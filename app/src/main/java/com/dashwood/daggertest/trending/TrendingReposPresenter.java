package com.dashwood.daggertest.trending;

import android.util.Log;

import com.dashwood.daggertest.di.ScreenScope;
import com.dashwood.daggertest.adapter.AdapterRecItemRepo;
import com.dashwood.daggertest.data.RepoRequester;
import com.dashwood.daggertest.model.Repo;

import javax.inject.Inject;

@ScreenScope
class TrendingReposPresenter implements AdapterRecItemRepo.RepoClickedListener {

    private TrendingReposViewModel viewModel;
    private RepoRequester repoRequester;

    @Inject
    TrendingReposPresenter(TrendingReposViewModel viewModel, RepoRequester repoRequester) {
        this.viewModel = viewModel;
        this.repoRequester = repoRequester;
        loadingRepos();
    }

    private void loadingRepos() {
        repoRequester.getTrendingRepos()
                .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((d, t) -> viewModel.loadingUpdated().accept(false))
                .subscribe(viewModel.reposUpdated(), viewModel.errorUpdated());
    }

    @Override
    public void onRepoClicked(Repo repo) {
        Log.i("LOG", repo.name());
    }
}
