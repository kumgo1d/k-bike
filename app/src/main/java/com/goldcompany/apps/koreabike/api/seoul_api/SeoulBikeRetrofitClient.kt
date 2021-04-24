package com.goldcompany.apps.koreabike.api.seoul_api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SeoulBikeRetrofitClient {
    private val retrofitClient: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(SeoulOpenApi.DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
    }
    val seoulOpenService: SeoulOpenService by lazy {
        retrofitClient.build().create(SeoulOpenService::class.java)
    }
}