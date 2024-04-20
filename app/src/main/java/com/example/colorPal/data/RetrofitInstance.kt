package com.example.colorPal.data

import com.example.colorPal.network.ColorApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://www.thecolorapi.com"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val colorApiService: ColorApiService by lazy {
        retrofit.create(ColorApiService::class.java)
    }

}