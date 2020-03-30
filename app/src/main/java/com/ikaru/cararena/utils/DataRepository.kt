package com.ikaru.cararena.utils

import com.ikaru.cararena.services.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataRepository {
    public fun create(): ApiService {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://139.162.28.184:4000/api/")
            .build()
        return retrofit.create(ApiService::class.java)
    }
}