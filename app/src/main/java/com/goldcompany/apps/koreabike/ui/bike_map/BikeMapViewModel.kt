package com.goldcompany.apps.koreabike.ui.bike_map

import android.app.Application
import androidx.lifecycle.*
import com.goldcompany.apps.koreabike.data.KBikeRepository
import com.goldcompany.apps.koreabike.data.category_group.CategoryGroup

class BikeMapViewModel(application: Application) : AndroidViewModel(application) {
    private val kBikeRepository =  KBikeRepository.getRepository(application)

    fun getItem(code: String, longitude: String, latitude: String): LiveData<CategoryGroup> {
        return kBikeRepository.getCategoryItem(code, longitude, latitude)
    }
}