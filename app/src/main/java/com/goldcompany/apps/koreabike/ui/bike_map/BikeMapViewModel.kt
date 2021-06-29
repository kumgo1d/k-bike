package com.goldcompany.apps.koreabike.ui.bike_map

import androidx.lifecycle.*
import com.goldcompany.apps.koreabike.data.category_group.CategoryGroup
import com.goldcompany.apps.koreabike.api.seoul_api.SeoulBikeRepository

class BikeMapViewModel : ViewModel() {
    fun getItem(code: String, longitude: String, latitude: String): LiveData<CategoryGroup> {
        return SeoulBikeRepository.getCategoryItem(code, longitude, latitude)
    }
}