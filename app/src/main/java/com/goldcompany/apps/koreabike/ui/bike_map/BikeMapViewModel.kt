package com.goldcompany.apps.koreabike.ui.bike_map

import android.os.Bundle
import androidx.lifecycle.*
import com.goldcompany.apps.koreabike.data.category_group.CategoryGroup

class BikeMapViewModel : ViewModel() {
    val bundle = Bundle()
    fun getItem(code: String, longitude: String, latitude: String): LiveData<CategoryGroup> {
        return BikeMapRepository.getCategoryItem(code, longitude, latitude)
    }
}