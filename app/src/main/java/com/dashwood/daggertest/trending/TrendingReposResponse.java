package com.dashwood.daggertest.trending;

import android.util.Log;

import com.dashwood.daggertest.model.Repo;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

@AutoValue
public abstract class TrendingReposResponse {
    @Json(name = "items")
    public abstract List<Repo> repos();

    public static JsonAdapter<TrendingReposResponse> jsonAdapter(Moshi moshi) {
        Log.i("LOG", "Json adapter f rom trending repos response");
        return new AutoValue_TrendingReposResponse.MoshiJsonAdapter(moshi);
    }
}
