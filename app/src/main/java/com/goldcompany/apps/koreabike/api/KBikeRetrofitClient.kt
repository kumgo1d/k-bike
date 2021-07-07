package com.goldcompany.apps.koreabike.api

import com.goldcompany.apps.koreabike.Constants
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object KakaoApiRetrofitClient {
    private val retrofit: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.KAKAO_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    }

    val apiService: KakaoApiService by lazy {
        retrofit.build().create(KakaoApiService::class.java)
    }
}

object NaverApiRetrofitClient {
    private val retrofit: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.NAVER_API)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    }

    val naverApi: NaverApiService by lazy {
        retrofit.build().create(NaverApiService::class.java)
    }
}