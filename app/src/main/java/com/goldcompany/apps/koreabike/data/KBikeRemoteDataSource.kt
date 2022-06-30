package com.goldcompany.apps.koreabike.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.goldcompany.apps.koreabike.BuildConfig
import com.goldcompany.apps.koreabike.api.KakaoApiService
import com.goldcompany.apps.koreabike.api.NaverApiService
import com.goldcompany.apps.koreabike.data.driving.ResultPath
import com.goldcompany.apps.koreabike.data.place_marker.PlaceMarker
import com.goldcompany.apps.koreabike.data.search_address.AddressItem
import com.goldcompany.apps.koreabike.data.search_address.Addresses
import com.goldcompany.apps.koreabike.data.search_address.SearchAddressPagingSource
import com.goldcompany.apps.koreabike.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KBikeRemoteDataSource @Inject constructor(
    private val kakaoApiService: KakaoApiService,
    private val naverApiService: NaverApiService
) {
    private val KAKAO_API_KEY = BuildConfig.KAKAO_API_KEY
    private val NAVER_API_CLIENT_ID = BuildConfig.NAVER_API_CLIENT_ID
    private val NAVER_API_KEY = BuildConfig.NAVER_API_KEY
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    suspend fun searchAddress(address: String, page: Int): Addresses = withContext(Dispatchers.IO) {
        return@withContext kakaoApiService.searchAddress(KAKAO_API_KEY, address = address, page)
    }

    fun getSearchAddressStream(address: String): Flow<PagingData<AddressItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 1,
                initialLoadSize = 10,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { SearchAddressPagingSource(kakaoApiService, address) }
        ).flow
    }

    suspend fun searchNearbyPlacesMarker(
        code: String,
        longitude: String,
        latitude: String
    ): Result<PlaceMarker> = withContext(ioDispatcher) {
        try {
            val markers = kakaoApiService.searchNearbyPlacesMarker(KAKAO_API_KEY, code, longitude, latitude, radius = 10000)
            if(markers != null) {
                return@withContext Result.Success(markers)
            } else {
                return@withContext Result.Error(Exception("PlaceMarker is Not Found"))
            }
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    suspend fun getNavigationPath(start: String, end: String): Result<ResultPath> = withContext(ioDispatcher) {
        try {
            val path = naverApiService.getPath(
                NAVER_API_CLIENT_ID, NAVER_API_KEY, start, end
            )
            if(path != null) {
                return@withContext Result.Success(path)
            } else {
                return@withContext Result.Error(Exception("Path Not Found"))
            }
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }
}