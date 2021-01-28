package com.goldcompany.apps.koreabike.findrestaurants.kakaoapi

import com.goldcompany.apps.koreabike.findrestaurants.kakaodata.KakaoData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

class KakaoApi {
    companion object {
        const val BASE_URL = "https://dapi.kakao.com/"
        const val API_KEY = "09ab5a332869126358f643b6ff26abc8"
    }
}

interface KakaoApiService {
    @GET("v2/local/search/keyword.json")
    fun getKakaoAddress(
        @Header("Authorization") key: String,
        @Query("query") address: String
    ): Call<KakaoData>
}