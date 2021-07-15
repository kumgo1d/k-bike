package com.goldcompany.apps.koreabike.api

import com.goldcompany.apps.koreabike.data.category_group.CategoryGroup
import com.goldcompany.apps.koreabike.data.driving.ResultPath
import com.goldcompany.apps.koreabike.data.kakaodata.KakaoData
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoApiService {
    @GET("v2/local/search/keyword.json")
    fun getKakaoAddress(
        @Header("Authorization") key: String,
        @Query("query") address: String
    ): Observable<KakaoData>

    @GET("v2/local/search/category.json")
    fun getCategoryGroup(
        @Header("Authorization") key: String,
        @Query("category_group_code") code: String,
        @Query("x") longitude: String,
        @Query("y") latitude: String,
        @Query("radius") radius: Int
    ): Call<CategoryGroup>
}

interface NaverApiService {
    @GET("v1/driving")
    fun getPath(
        @Header("X-NCP-APIGW-API-KEY-ID") apiKeyId: String,
        @Header("X-NCP-APIGW-API-KEY") apiKey: String,
        @Query("start") start: String,
        @Query("goal") goal: String
    ): Call<ResultPath>
}