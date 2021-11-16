package com.dashwood.daggertest.advancedandroid;

import com.ryanharter.auto.value.moshi.MoshiAdapterFactory;
import com.squareup.moshi.Moshi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.util.Objects;

import com.dashwood.daggertest.model.ZoneDateTimeAdapter;
import com.dashwood.daggertest.adapter.AdapterRecItemRepo;

public class TestUtils {
    private static final TestUtils INSTANCE = new TestUtils();
    private static final Moshi TEST_MOSHI = initializeMoshi();

    private TestUtils() {

    }

    public static <T> T loadJson(String path, Type type) {
        try {
            String json = getStringFilePath(path);
            return (T) TEST_MOSHI.adapter(type).fromJson(json);
        } catch (IOException e) {
            throw new IllegalArgumentException("Path : " + path + " Type : " + type + " Exception : " + e.getLocalizedMessage());
        }
    }

    public static <T> T loadJson(String path, Class<T> clazz) {
        try {
            String json = getStringFilePath(path);
            return TEST_MOSHI.adapter(clazz).fromJson(json);
        } catch (IOException e) {
            throw new IllegalArgumentException("Path : " + path + " Type : " + clazz + " Exception : " + e.getLocalizedMessage());
        }
    }

    private static String getStringFilePath(String path) {
        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader
                    (Objects.requireNonNull(INSTANCE.getClass().getClassLoader()).getResourceAsStream(path)));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalArgumentException("Path : " + path + " Ex : " + e.getLocalizedMessage());
        }
    }

    private static Moshi initializeMoshi() {
        Moshi.Builder builder = new Moshi.Builder();
        builder.add(AdapterRecItemRepo.create());
        builder.add(new ZoneDateTimeAdapter());
        return builder.build();
    }

}
