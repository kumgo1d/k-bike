package com.goldcompany.apps.koreabike.ui.bike_map

import androidx.lifecycle.*
import com.goldcompany.apps.koreabike.data.category_group.CategoryGroup

class BikeMapViewModel : ViewModel() {
    val pharmacyCode = "PM9"
    val convenienceStoreCode = "CS2"
    val cafeCode = "CE7"
    val accommodationCode = "AD5"

    fun getItem(code: String, longitude: String, latitude: String): LiveData<CategoryGroup> {
        return BikeMapRepository.getCategoryItem(code, longitude, latitude)
    }
}