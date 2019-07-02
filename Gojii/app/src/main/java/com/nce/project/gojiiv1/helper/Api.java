package com.nce.project.gojiiv1.helper;



import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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





}

