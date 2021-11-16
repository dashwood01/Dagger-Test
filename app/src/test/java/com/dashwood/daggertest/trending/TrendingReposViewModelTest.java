package com.dashwood.daggertest.trending;

import static com.dashwood.daggertest.advancedandroid.TestUtils.loadJson;

import com.dashwood.daggertest.R;
import com.dashwood.daggertest.advancedandroid.TestUtils;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;

import io.reactivex.rxjava3.observers.TestObserver;

public class TrendingReposViewModelTest {

    private TrendingReposViewModel viewModel;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testLoading() throws Throwable {
        TestObserver<Boolean> loadingObserver = viewModel.loading().test();
        viewModel.loadingUpdated().accept(true);
        viewModel.loadingUpdated().accept(false);
        loadingObserver.assertValues(true, false);
    }

    @Test
    public void testRepos() throws Throwable {
        TrendingReposResponse response =  loadJson("mock/get_trending_repos.json",
                TrendingReposResponse.class);
        viewModel.reposUpdated().accept(response.repos());

        viewModel.repos().test().assertValue(response.repos());
    }

    @Test
    public void testError() throws Throwable {
        TestObserver<Integer> errorObserver = viewModel.error().test();
        viewModel.errorUpdated().accept(new IOException());
        viewModel.reposUpdated().accept(Collections.emptyList());

        errorObserver.assertValues(R.string.error_repos, -1);
    }
}