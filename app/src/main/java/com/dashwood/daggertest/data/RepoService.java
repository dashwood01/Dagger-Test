package com.dashwood.daggertest.data;

import com.dashwood.daggertest.trending.TrendingReposResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface RepoService {
    @GET("search/repositories?q=language:java&order=desc&sort=stars")
    Single<TrendingReposResponse> getTrendingRepos();
}
