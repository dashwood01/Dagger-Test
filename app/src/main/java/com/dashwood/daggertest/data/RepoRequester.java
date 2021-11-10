package com.dashwood.daggertest.data;

import com.dashwood.daggertest.model.Repo;
import com.dashwood.daggertest.trending.TrendingReposResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class RepoRequester {
    private final RepoService repoService;

    @Inject
    RepoRequester(RepoService repoService) {
        this.repoService = repoService;
    }

    public Single<List<Repo>> getTrendingRepos(){
        return repoService.getTrendingRepos()
                .map(TrendingReposResponse::repos)
                .subscribeOn(Schedulers.io());
    }
}
