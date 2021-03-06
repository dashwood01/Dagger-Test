package com.dashwood.daggertest.data;

import android.util.Log;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public abstract class RepoServiceModule {
    @Provides
    @Singleton
    static RepoService provideRepoService(Retrofit retrofit) {
        Log.i("LOG", "provide repo service");
        return retrofit.create(RepoService.class);
    }
}
