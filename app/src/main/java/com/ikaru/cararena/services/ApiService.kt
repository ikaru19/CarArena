package com.ikaru.cararena.services

import com.ikaru.cararena.models.BackgroundModels
import com.ikaru.cararena.models.BrandModel
import com.ikaru.cararena.models.CarModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("brand/cars")
    fun getCar(): Call<List<CarModel>>

    @GET("brand")
    fun getBrands(): Call<List<BrandModel>>

    @GET("backgrounds")
    fun getBackgrounds(): Call<List<BackgroundModels>>
}