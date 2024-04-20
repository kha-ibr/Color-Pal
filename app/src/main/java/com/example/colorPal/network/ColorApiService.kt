package com.example.colorPal.network

import com.example.colorPal.model.ColorApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ColorApiService {
    @GET("/scheme")
    suspend fun getColorsByHex(
        @Query("hex") hex: String,
        @Query("format") format: String = "json",
        @Query("mode") mode: String = "monochrome",
        @Query("count") count: Int = 6
    ): ColorApiResponse
}