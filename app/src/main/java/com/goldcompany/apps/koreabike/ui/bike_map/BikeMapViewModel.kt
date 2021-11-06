package com.goldcompany.apps.koreabike.ui.bike_map

import androidx.lifecycle.*
import com.goldcompany.apps.koreabike.api.FindPlaces
import com.goldcompany.apps.koreabike.data.category_group.CategoryGroup

class BikeMapViewModel : ViewModel() {
    fun getItem(code: String, longitude: String, latitude: String): LiveData<CategoryGroup> {
        return BikeMapRepository().getCategoryItem(code, longitude, latitude)
    }
}