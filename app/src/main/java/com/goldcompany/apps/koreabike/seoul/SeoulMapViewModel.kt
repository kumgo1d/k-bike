package com.goldcompany.apps.koreabike.seoul

import androidx.lifecycle.*
import com.goldcompany.apps.koreabike.find_places.CategoryGroup.CategoryGroup
import com.goldcompany.apps.koreabike.seoul.seoul_api.SeoulRepository
import com.goldcompany.apps.koreabike.seoul_bike_data.SeoulBike

class SeoulMapViewModel : ViewModel() {
    private var bikes1 = MutableLiveData<SeoulBike>()
    private var bikes2 = MutableLiveData<SeoulBike>()
    private var bikes3 = MutableLiveData<SeoulBike>()

    fun getBikes1(): LiveData<SeoulBike> {
        bikes1 = SeoulRepository.getBikeData1()
        return bikes1
    }

    fun getBikes2(): LiveData<SeoulBike> {
        bikes2 = SeoulRepository.getBikeData2()
        return bikes2
    }

    fun getBikes3(): LiveData<SeoulBike> {
        bikes3 = SeoulRepository.getBikeData3()
        return bikes3
    }

    fun getItem(code: String, longitude: String, latitude: String): LiveData<CategoryGroup> {
        return SeoulRepository.getCategoryItem(code, longitude, latitude)
    }
}