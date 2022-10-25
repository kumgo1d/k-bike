package com.goldcompany.koreabike.data.api

import com.goldcompany.koreabike.data.model.address.ApiAddress
import com.goldcompany.koreabike.data.model.place.ApiPlaceMarkerResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoApiService {
    @GET("v2/local/search/keyword.json")
    fun searchAddress(
        @Header("Authorization") key: String,
        @Query("query") address: String,
        @Query("page") page: Int
    ): Flow<List<ApiAddress>>

    @GET("v2/local/search/category.json")
    suspend fun searchNearbyPlacesMarker(
        @Header("Authorization") key: String,
        @Query("category_group_code") code: String,
        @Query("x") longitude: String,
        @Query("y") latitude: String,
        @Query("radius") radius: Int
    ): ApiPlaceMarkerResponse
}