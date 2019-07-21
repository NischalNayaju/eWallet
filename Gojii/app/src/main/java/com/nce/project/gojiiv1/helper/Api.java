package com.nce.project.gojiiv1.helper;



import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("v1/auth/login")
    Observable<String> login(
            @Field( "email" ) String email,
            @Field("password") String password

    );


    @FormUrlEncoded
    @POST("v1/auth/register")

    Observable<String>
    register(
            @Field( "email" ) String email,
            @Field("password") String password,
            @Field("name") String name

    );

    @FormUrlEncoded
    @POST("v1/ewallet/deposit")

    Observable<String>
    load(   @Header("Authorization") String accessToken,
            @Field( "card" ) String card,
            @Field("amount") String amount


    );


    @GET("v1/ewallet/transactions")
    Observable<ArrayList<Transaction>>
    transaction(
            @Header("Authorization") String accessToken



    );




}

