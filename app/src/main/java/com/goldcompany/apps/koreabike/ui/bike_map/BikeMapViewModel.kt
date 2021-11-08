package com.goldcompany.apps.koreabike.ui.bike_map

import android.app.Application
import androidx.lifecycle.*
import com.goldcompany.apps.koreabike.data.KBikeRepository
import com.goldcompany.apps.koreabike.data.category_group.CategoryGroup
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BikeMapViewModel(application: Application) : AndroidViewModel(application) {
    private val kBikeRepository =  KBikeRepository.getRepository(application)

    suspend fun getItem(code: String, longitude: String, latitude: String): Flow<CategoryGroup> = flow {
        emit(kBikeRepository.getCategoryItem(code, longitude, latitude))
    }
}