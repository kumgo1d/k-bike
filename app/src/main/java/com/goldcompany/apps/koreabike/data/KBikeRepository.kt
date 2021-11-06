package com.goldcompany.apps.koreabike.data

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.goldcompany.apps.koreabike.data.category_group.CategoryGroup
import com.goldcompany.apps.koreabike.api.FindPlaces
import com.goldcompany.apps.koreabike.data.kakaodata.KakaoAddressItem
import com.goldcompany.apps.koreabike.data.kakaodata.KakaoData
import com.goldcompany.apps.koreabike.db.history_address.HistoryAddressLocalDataSource
import com.goldcompany.apps.koreabike.db.KBikeDatabase
import com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.disposables.Disposable
import java.lang.Exception

class KBikeRepository private constructor(application: Application) {

    private val findPlaces: FindPlaces
    private val addressLocalDataSource: HistoryAddressLocalDataSource

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

        findPlaces = FindPlaces()
        addressLocalDataSource = HistoryAddressLocalDataSource(database.UserAddressDAO())
    }

    suspend fun getKeywordAddressItem(address: String): List<KakaoAddressItem> {
        return findPlaces.callKakaoKeyword(address)
    }

    suspend fun getCategoryItem(code: String, longitude: String, latitude: String): MutableLiveData<CategoryGroup> {
        return findPlaces.callKakaoCategoryGroupItem(code, longitude, latitude)
    }

    suspend fun getAllAddress(): MutableList<UserHistoryAddress> {
        return addressLocalDataSource.getAllAddress()
    }
}