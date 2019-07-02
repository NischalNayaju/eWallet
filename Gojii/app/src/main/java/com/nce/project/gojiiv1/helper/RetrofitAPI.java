package com.nce.project.gojiiv1.helper;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitAPI {

    public static Retrofit instance;

    public static Retrofit getInstance(){

        if(instance==null)
            instance = new Retrofit.Builder()
                    .baseUrl("http://192.168.137.197:3000/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        return instance;
    }


}
