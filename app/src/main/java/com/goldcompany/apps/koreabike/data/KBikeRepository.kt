package com.goldcompany.apps.koreabike.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.goldcompany.apps.koreabike.data.place_marker.PlaceMarker
import com.goldcompany.apps.koreabike.api.KakaoApiService
import com.goldcompany.apps.koreabike.api.NaverApiService
import com.goldcompany.apps.koreabike.data.driving.ResultPath
import com.goldcompany.apps.koreabike.data.search_address.AddressItem
import com.goldcompany.apps.koreabike.data.search_address.Addresses
import com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress
import com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddressDAO
import com.goldcompany.apps.koreabike.data.search_address.SearchAddressPagingSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.Exception

@Singleton
class KBikeRepository @Inject constructor(
    private val kakaoApiService: KakaoApiService,
    private val naverApiService: NaverApiService,
    private val addressDao: UserHistoryAddressDAO
) {
    private val KAKAO_API_KEY = "KakaoAK 09ab5a332869126358f643b6ff26abc8"
    private val NAVER_API_CLIENT_ID = "fe7iwsbkl5"
    private val NAVER_API_KEY = "1KYsy93nxRaNmfxdHExFfyAIX89B8sfwePQw7bNP"
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

    suspend fun getNavigationPath(start: String, goal: String): Result<ResultPath> = withContext(ioDispatcher) {
        try {
            val path = naverApiService.getPath(
                NAVER_API_CLIENT_ID, NAVER_API_KEY, start, goal, "tracomfort"
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

    suspend fun getAllAddress(): MutableList<UserHistoryAddress> = withContext(ioDispatcher) {
        return@withContext addressDao.getAll()
    }

    suspend fun getAddress(): UserHistoryAddress? = withContext(ioDispatcher) {
        return@withContext addressDao.getAddress()
    }

    suspend fun updateAddressUnselect(date: Long) = withContext(ioDispatcher) {
        addressDao.updateAddressUnselect(date)
    }

    suspend fun insertAddress(address: UserHistoryAddress) = withContext(ioDispatcher) {
        addressDao.insert(address)
    }

    suspend fun deleteAddress(address: UserHistoryAddress) = withContext(ioDispatcher) {
        addressDao.delete(address)
    }
}