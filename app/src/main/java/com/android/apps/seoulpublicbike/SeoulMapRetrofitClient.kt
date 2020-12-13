package com.android.apps.seoulpublicbike

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object SeoulMapRetrofitClient {
//    companion object {
//        //Retrofit 사용
//        private val retrofit = Retrofit.Builder()
//            .baseUrl(SeoulOpenApi.DOMAIN)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        val seoulOpenService = retrofit.create(SeoulOpenService::class.java)
//    }
    private val retrofitClient: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(SeoulOpenApi.DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
    }
    val seoulOpenService: SeoulOpenService by lazy {
        retrofitClient.build().create(SeoulOpenService::class.java)
    }
}