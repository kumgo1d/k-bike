package com.goldcompany.apps.koreabike.ui.bike_map

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.goldcompany.apps.koreabike.data.category_group.CategoryGroup
import com.goldcompany.apps.koreabike.api.FindPlaces
import com.goldcompany.apps.koreabike.db.KBikeDatabase

class BikeMapRepository {

//    companion object {
//        @Volatile
//        private var INSTANCE: BikeMapRepository? = null
//
//        fun getRepository(app: Application): BikeMapRepository {
//            return INSTANCE ?: synchronized(this) {
//                BikeMapRepository(app).also {
//                    INSTANCE = it
//                }
//            }
//        }
//    }
//
//    init {
//        val database by lazy { KBikeDatabase.getInstance(application.applicationContext) }
//    }

    fun getCategoryItem(code: String, longitude: String, latitude: String): MutableLiveData<CategoryGroup> {
        return FindPlaces().callKakaoCategoryGroupItem(code, longitude, latitude)
    }
}