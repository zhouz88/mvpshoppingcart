package com.example.mvp.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static volatile RetrofitClient mIntance;
    private Retrofit retrofit;
    private static final String BASE_URL = "https://raw.githubusercontent.com/";

    private RetrofitClient() {
    }

    public static RetrofitClient getInstance() {
        if (mIntance == null) {
            synchronized (RetrofitClient.class) {
                if (mIntance == null) {
                    mIntance = new RetrofitClient();
                }
            }
        }
        return mIntance;
    }

    public <T> T getService(Class<T> clazz) {
        return getRetrofit().create(clazz);
    }

    private synchronized Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    //  .callFactory(new OkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
