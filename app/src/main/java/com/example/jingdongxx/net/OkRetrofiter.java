package com.example.jingdongxx.net;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 */

public class OkRetrofiter {
    private static OkHttpClient clients;
    private static RetrofitAPI retrofitAPI;

    static {
        getClients();
    }

    public static OkHttpClient getClients() {
        if (clients == null) {
            synchronized (RetrofitAPI.class) {
                if (clients == null) {
                    clients = new OkHttpClient();
                }
            }
        }
        return clients;
    }

    public static RetrofitAPI getAPI() {
        if (retrofitAPI == null) {
            synchronized (OkHttpClient.class) {
                if (retrofitAPI == null) {
                    retrofitAPI = onCreate(RetrofitAPI.class, Api.HOST);
                }
            }
        }
        return retrofitAPI;
    }

    public static <T> T onCreate(Class<T> tClass, String url) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(clients)
                .build();
        return retrofit.create(tClass);
    }

}
