package com.goldcompany.apps.koreabike.api.kakao_api

import com.goldcompany.apps.koreabike.data.CategoryGroup.CategoryGroup
import com.goldcompany.apps.koreabike.data.kakaodata.KakaoData
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

class KakaoApi {
    companion object {
        const val BASE_URL = "https://dapi.kakao.com/"
        const val API_KEY = "KakaoAK 09ab5a332869126358f643b6ff26abc8"
    }
}

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