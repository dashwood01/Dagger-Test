package com.dashwood.daggertest.extra;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dashwood.daggertest.MainActivity;
import com.dashwood.daggertest.di.ActivityInjector;
import com.squareup.moshi.Moshi;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjector;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import okhttp3.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module
public class ActivityInjectorModule {
    private ActivityInjector activityInjector;
    private Provider<MainActivityModule_ContributeMainActivity.MainActivitySubcomponent.Builder>
            mainActivitySubcomponentBuilderProvider;

    public ActivityInjectorModule() {
        mainActivitySubcomponentBuilderProvider =
                MainActivitySubcomponentBuilder::new;
        activityInjector = new ActivityInjector(MapBuilder
                .<Class<? extends AppCompatActivity>, Provider<AndroidInjector.Factory<? extends AppCompatActivity>>>
                        newMapBuilder(1)
                .put(MainActivity.class, (Provider) mainActivitySubcomponentBuilderProvider)
                .build());
    }

    @Provides
    @Singleton
    public ActivityInjector provideActivityInjector() {
        return activityInjector;
    }

    class MainActivitySubcomponentBuilder
            extends MainActivityModule_ContributeMainActivity.MainActivitySubcomponent.Builder {
        private MainActivity seedInstance;

        @Override
        public void seedInstance(Object instance) {
            this.seedInstance = (MainActivity) Preconditions.checkNotNull(instance);
        }

        @Override//The override here is Android Injector. Builder. build ()
        public MainActivityModule_ContributeMainActivity.MainActivitySubcomponent build() {
            if (seedInstance == null) {
                throw new IllegalStateException(MainActivity.class.getCanonicalName() + " must be set");
            }
            return new MainActivitySubcomponentImpl(this);
        }
    }

    class MainActivitySubcomponentImpl
            implements MainActivityModule_ContributeMainActivity.MainActivitySubcomponent {
        private MainActivitySubcomponentImpl(MainActivitySubcomponentBuilder builder) {}

        @Override//Here is Android Injector. inject ()
        public void inject(MainActivity arg0) {}
    }
}
