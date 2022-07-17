package com.goldcompany.apps.koreabike.api

import com.goldcompany.apps.koreabike.Constants
import com.goldcompany.koreabike.data.api.KakaoApiService
import com.goldcompany.koreabike.data.model.place.ApiPlaceMarkerResponse
import com.goldcompany.koreabike.data.model.address.ApiAddressResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoApiService {
    @GET("v2/local/search/keyword.json")
    suspend fun searchAddress(
        @Header("Authorization") key: String,
        @Query("query") address: String,
        @Query("page") page: Int
    ): ApiAddressResponse

    @GET("v2/local/search/category.json")
    suspend fun searchNearbyPlacesMarker(
        @Header("Authorization") key: String,
        @Query("category_group_code") code: String,
        @Query("x") longitude: String,
        @Query("y") latitude: String,
        @Query("radius") radius: Int
    ): ApiPlaceMarkerResponse?

    companion object {
        fun create(): KakaoApiService {
            val client = OkHttpClient.Builder()
                .build()

            return Retrofit.Builder()
                .baseUrl(Constants.KAKAO_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(KakaoApiService::class.java)
        }
    }
}