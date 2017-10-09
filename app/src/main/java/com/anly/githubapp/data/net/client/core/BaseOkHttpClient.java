package com.anly.githubapp.data.net.client.core;

import com.anly.githubapp.BuildConfig;
import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by mingjun on 16/7/20.
 */
public abstract class BaseOkHttpClient {

    private static final long TIMEOUT_CONNECT = 30 * 1000;

    public OkHttpClient get() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(TIMEOUT_CONNECT, TimeUnit.MILLISECONDS)
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Logger.d(message);
                    }
                }).setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE));

        builder = customize(builder);

        return builder.build();
    }

    public abstract OkHttpClient.Builder customize(OkHttpClient.Builder builder);

}
