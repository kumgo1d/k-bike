package com.goldcompany.apps.koreabike.data

import android.app.Application
import com.goldcompany.apps.koreabike.data.place_marker.PlaceMarker
import com.goldcompany.apps.koreabike.api.KakaoApiRetrofitClient
import com.goldcompany.apps.koreabike.api.KakaoApiService
import com.goldcompany.apps.koreabike.api.NaverApiRetrofitClient
import com.goldcompany.apps.koreabike.api.NaverApiService
import com.goldcompany.apps.koreabike.data.driving.ResultPath
import com.goldcompany.apps.koreabike.data.search_address.Addresses
import com.goldcompany.apps.koreabike.db.history_address.HistoryAddressLocalDataSource
import com.goldcompany.apps.koreabike.db.KBikeDatabase
import com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class KBikeRepository private constructor(application: Application) {

    private val kakaoApiService: KakaoApiService
    private val naverApiService: NaverApiService
    private val addressLocalDataSource: HistoryAddressLocalDataSource

    private val KAKAO_API_KEY = "KakaoAK 09ab5a332869126358f643b6ff26abc8"
    private val NAVER_API_KEY_ID = "fe7iwsbkl5"
    private val NAVER_API_KEY = "1KYsy93nxRaNmfxdHExFfyAIX89B8sfwePQw7bNP"

    companion object {
        @Volatile
        private var INSTANCE: KBikeRepository? = null

        fun getRepository(app: Application): KBikeRepository {
            return INSTANCE ?: synchronized(this) {
                KBikeRepository(app).also {
                    INSTANCE = it
                }
            }
        }
    }

    init {
        val database by lazy { KBikeDatabase.getInstance(application.applicationContext) }

        kakaoApiService = KakaoApiRetrofitClient.provideKakaoApiService()
        naverApiService = NaverApiRetrofitClient.provideNaverApiService()
        addressLocalDataSource = HistoryAddressLocalDataSource(database.userAddressDAO())
    }

    suspend fun searchAddress(address: String): Addresses = withContext(Dispatchers.IO) {
        return@withContext kakaoApiService.searchAddress(KAKAO_API_KEY, address = address)
    }

    suspend fun searchNearbyPlacesMarker(
        code: String,
        longitude: String,
        latitude: String
    ): PlaceMarker = withContext(Dispatchers.IO) {
        return@withContext kakaoApiService.searchNearbyPlacesMarker(KAKAO_API_KEY, code, longitude, latitude, radius = 10000)
    }

    suspend fun getNavigationPath(start: String, goal: String): ResultPath = withContext(Dispatchers.IO) {
        return@withContext naverApiService.getPath(
            NAVER_API_KEY_ID, NAVER_API_KEY, start, goal, "tracomfort"
        )
    }

    suspend fun getAllAddress(): MutableList<UserHistoryAddress> {
        return addressLocalDataSource.getAllAddress()
    }

    suspend fun getAddress(): UserHistoryAddress {
        return addressLocalDataSource.getAddress()
    }

    suspend fun updateAddressUnselect(date: Long) {
        addressLocalDataSource.updateAddressUnselect(date)
    }

    suspend fun insertAddress(address: UserHistoryAddress) {
        addressLocalDataSource.insertAddress(address)
    }

    suspend fun deleteAddress(address: UserHistoryAddress) {
        addressLocalDataSource.deleteAddress(address)
    }
}