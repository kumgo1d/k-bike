package com.goldcompany.koreabike.data.repository.remote

import com.goldcompany.koreabike.data.BuildConfig
import com.goldcompany.koreabike.data.api.KakaoApiService
import com.goldcompany.koreabike.data.api.NaverApiService
import com.goldcompany.koreabike.data.model.address.ApiAddress
import com.goldcompany.koreabike.data.model.address.ApiAddressResponse
import com.goldcompany.koreabike.data.model.driving.ApiNavigationPathResponse
import com.goldcompany.koreabike.data.model.place.ApiPlaceMarkerResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Singleton

@Singleton
interface KBikeRemoteDataSource {
    fun searchAddress(address: String, page: Int): Flow<List<ApiAddress>>

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

    override fun searchAddress(address: String, page: Int): Flow<List<ApiAddress>> {
        return kakaoApiService.searchAddress(
            KAKAO_API_KEY,
            address = address,
            page = page
        )
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