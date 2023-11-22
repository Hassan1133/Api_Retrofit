package com.example.retrofitpractice;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {
    @FormUrlEncoded
    @POST("insertData.php")
    Call<GetResponse> insertData(
            @Field("name") String name,
            @Field("father_name") String fatherName,
            @Field("department") String department,
            @Field("email") String email
    );

    @GET("fetchData.php")
    Call<List<User>> fetchData();

    @FormUrlEncoded
    @POST("updateData.php")
    Call<GetResponse> updateData(
            @Field("id") int id,
            @Field("name") String name,
            @Field("father_name") String fatherName,
            @Field("department") String department,
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<GetResponse> deleteData(
            @Field("id") int id
            );
}
