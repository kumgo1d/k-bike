package com.goldcompany.apps.koreabike.ui.bike_map

import androidx.lifecycle.*
import com.goldcompany.apps.koreabike.data.CategoryGroup.CategoryGroup
import com.goldcompany.apps.koreabike.api.seoul_api.SeoulBikeRepository
import com.goldcompany.apps.koreabike.data.seoul.SeoulBike
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage

class BikeMapViewModel : ViewModel() {
    private var bikes1 = MutableLiveData<SeoulBike>()
    private var bikes2 = MutableLiveData<SeoulBike>()
    private var bikes3 = MutableLiveData<SeoulBike>()

    lateinit var naverMap: NaverMap

    fun getBikes1(): LiveData<SeoulBike> {
        bikes1 = SeoulBikeRepository.getBikeData1()
        return bikes1
    }

    fun getBikes2(): LiveData<SeoulBike> {
        bikes2 = SeoulBikeRepository.getBikeData2()
        return bikes2
    }

    fun getBikes3(): LiveData<SeoulBike> {
        bikes3 = SeoulBikeRepository.getBikeData3()
        return bikes3
    }

    fun getItem(code: String, longitude: String, latitude: String): LiveData<CategoryGroup> {
        return SeoulBikeRepository.getCategoryItem(code, longitude, latitude)
    }
}