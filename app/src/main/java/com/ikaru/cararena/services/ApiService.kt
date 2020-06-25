package com.ikaru.cararena.services

import com.ikaru.cararena.models.*
import retrofit2.Call
import retrofit2.http.*


interface ApiService {
    @GET("brand/cars")
    fun getCar(): Call<List<CarModel>>

    @GET("brand/cars/date/{date}")
    fun getCarByDate(@Path("date") date : String): Call<List<CarModel>>;

    @GET("brand")
    fun getBrands(): Call<List<BrandModel>>

    @GET("review/cars/{id}")
    fun getReviewByID(@Path("id") id : Int): Call<List<ReviewModel>>;

    @GET("backgrounds")
    fun getBackgrounds(): Call<List<BackgroundModels>>

    @FormUrlEncoded
    @POST("auth/signin")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<UserModel>

    @FormUrlEncoded
    @POST("auth/signup")
    fun register(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<UserModel>

    @FormUrlEncoded
    @POST("review")
    fun postReview(
        @Field("name") username: String,
        @Field("review") review: String,
        @Field("generalId") generalId: Int
    ): Call<ReviewModel>
}