package com.goldcompany.apps.koreabike.data

import android.app.Application
import com.goldcompany.apps.koreabike.data.category_group.CategoryGroup
import com.goldcompany.apps.koreabike.api.KakaoApiRetrofitClient
import com.goldcompany.apps.koreabike.api.KakaoApiService
import com.goldcompany.apps.koreabike.data.kakaodata.KakaoData
import com.goldcompany.apps.koreabike.db.history_address.HistoryAddressLocalDataSource
import com.goldcompany.apps.koreabike.db.KBikeDatabase
import com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class KBikeRepository private constructor(application: Application) {

    private val kakaoApiService: KakaoApiService
    private val addressLocalDataSource: HistoryAddressLocalDataSource
    private val KAKAO_API_KEY = "KakaoAK 09ab5a332869126358f643b6ff26abc8"

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
        addressLocalDataSource = HistoryAddressLocalDataSource(database.UserAddressDAO())
    }

    suspend fun getKeywordAddressItem(address: String): KakaoData = withContext(Dispatchers.IO) {
        return@withContext kakaoApiService.getKakaoAddress(KAKAO_API_KEY, address = address)
    }

    suspend fun getCategoryItem(
        code: String,
        longitude: String,
        latitude: String
    ): CategoryGroup = withContext(Dispatchers.IO) {
        return@withContext kakaoApiService.getCategoryGroup(KAKAO_API_KEY, code, longitude, latitude, radius = 10000)
    }

    suspend fun getAllAddress(): MutableList<UserHistoryAddress> {
        return addressLocalDataSource.getAllAddress()
    }
}