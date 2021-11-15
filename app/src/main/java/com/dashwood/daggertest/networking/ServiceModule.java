package com.dashwood.daggertest.networking;

import android.util.Log;

import com.dashwood.daggertest.model.AdapterFactory;
import com.dashwood.daggertest.model.ZoneDateTimeAdapter;
import com.squareup.moshi.Moshi;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import debug.java.io.neverstoplearning.advancedandroid.networking.NetworkModule;
import okhttp3.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module(includes = NetworkModule.class)
public abstract class ServiceModule {
    @Provides
    @Singleton
    static Moshi provideMoshi() {
        Log.i("LOG", "Provide moshi");
        return new Moshi.Builder()
                .add(AdapterFactory.create())
                .add(new ZoneDateTimeAdapter())
                .build();
    }

    @Provides
    @Singleton
    static Retrofit provideRetrofit(Moshi moshi, Call.Factory callFactory, @Named("base_url") String baseUrl) {
        Log.i("LOG", "Provice retrofit from service module");
        return new Retrofit.Builder()
                .callFactory(callFactory)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }
}
