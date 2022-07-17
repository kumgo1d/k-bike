package com.goldcompany.apps.koreabike.api

import com.goldcompany.apps.koreabike.Constants
import com.goldcompany.koreabike.data.api.NaverApiService
import com.goldcompany.koreabike.data.model.driving.ApiNavigationPathResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NaverApiService {
    @GET("map-direction/v1/driving")
    suspend fun getPath(
        @Header("X-NCP-APIGW-API-KEY-ID") apiKeyId: String,
        @Header("X-NCP-APIGW-API-KEY") apiKey: String,
        @Query("start") start: String,
        @Query("goal") goal: String,
        @Query("option") option: String = "tracomfort"
    ): ApiNavigationPathResponse?

    companion object {
        fun create(): NaverApiService {
            val client = OkHttpClient.Builder()
                .build()

            return Retrofit.Builder()
                .baseUrl(Constants.NAVER_API)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NaverApiService::class.java)
        }
    }
}