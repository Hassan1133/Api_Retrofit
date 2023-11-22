package com.example.retrofitpractice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient client;
    private static Retrofit retrofit;

    private static final String BASE_URL = "http://192.168.18.41/student_signup_info/";

    public RetrofitClient() {
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (client == null) {
            client = new RetrofitClient();
        }
        return client;
    }

    public Api getApi() {
        return retrofit.create(Api.class);
    }
}