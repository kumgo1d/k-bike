package com.goldcompany.apps.koreabike.data

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.goldcompany.apps.koreabike.data.category_group.CategoryGroup
import com.goldcompany.apps.koreabike.api.FindPlaces
import com.goldcompany.apps.koreabike.data.kakaodata.KakaoData
import com.goldcompany.apps.koreabike.db.KBikeDatabase
import java.lang.Exception

class KBikeRepository private constructor(application: Application) {

    private val findPlaces: FindPlaces

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
    }

    fun getCategoryItem(code: String, longitude: String, latitude: String): MutableLiveData<CategoryGroup> {
        return findPlaces.callKakaoCategoryGroupItem(code, longitude, latitude)
    }

    fun getKeywordAddressItem(address: String, onComplete: (KakaoData?, Exception?) -> Unit) {
        return findPlaces.callKakaoKeyword(address, onComplete)
    }
}