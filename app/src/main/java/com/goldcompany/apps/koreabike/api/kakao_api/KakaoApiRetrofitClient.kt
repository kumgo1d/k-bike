package com.goldcompany.apps.koreabike.api.kakao_api

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object KakaoApiRetrofitClient {
    private val retrofit: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(KakaoApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    }

    val apiService: KakaoApiService by lazy {
        retrofit
            .build()
            .create(KakaoApiService::class.java)
    }
}