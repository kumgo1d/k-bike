package com.goldcompany.koreabike.data.repository.remote

import android.util.Log
import com.goldcompany.koreabike.data.BuildConfig
import com.goldcompany.koreabike.data.api.KakaoApiService
import com.goldcompany.koreabike.data.api.NaverApiService
import com.goldcompany.koreabike.data.model.address.ApiAddressResponse
import com.goldcompany.koreabike.data.model.driving.ApiNavigationPathResponse
import com.goldcompany.koreabike.data.model.place.ApiPlaceMarkerResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Singleton

@Singleton
interface KBikeRemoteDataSource {
    suspend fun searchAddress(address: String, page: Int): ApiAddressResponse

    suspend fun searchNearbyPlaces(
        code: String,
        longitude: String,
        latitude: String
    ): ApiPlaceMarkerResponse

    suspend fun getNavigationPath(start: String, end: String): ApiNavigationPathResponse
}

class KBikeRemoteDataSourceImpl(
    private val kakaoApiService: KakaoApiService,
    private val naverApiService: NaverApiService
): KBikeRemoteDataSource {
    private val KAKAO_API_KEY = BuildConfig.KAKAO_API_KEY
    private val NAVER_API_CLIENT_ID = BuildConfig.NAVER_API_CLIENT_ID
    private val NAVER_API_KEY = BuildConfig.NAVER_API_KEY
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun searchAddress(address: String, page: Int): ApiAddressResponse = withContext(ioDispatcher) {
        val call = kakaoApiService.searchAddress(
            KAKAO_API_KEY,
            address = address,
            page = page
        )
        Log.d("dfdfdf", "call api : $call")
        return@withContext call
    }

    override suspend fun searchNearbyPlaces(
        code: String,
        longitude: String,
        latitude: String
    ): ApiPlaceMarkerResponse = withContext(ioDispatcher) {
        return@withContext kakaoApiService.searchNearbyPlacesMarker(KAKAO_API_KEY, code, longitude, latitude, radius = 10000)
    }

    override suspend fun getNavigationPath(start: String, end: String): ApiNavigationPathResponse = withContext(ioDispatcher) {
        return@withContext naverApiService.getPath(NAVER_API_CLIENT_ID, NAVER_API_KEY, start, end)
    }
}