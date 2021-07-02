package com.goldcompany.apps.koreabike.ui.bike_map

import androidx.lifecycle.MutableLiveData
import com.goldcompany.apps.koreabike.data.category_group.CategoryGroup
import com.goldcompany.apps.koreabike.api.FindPlaces

object BikeMapRepository {
    fun getCategoryItem(code: String, longitude: String, latitude: String): MutableLiveData<CategoryGroup> {
        return FindPlaces().callKakaoCategoryGroupItem(code, longitude, latitude)
    }
}