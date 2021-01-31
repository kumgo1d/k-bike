package com.goldcompany.apps.koreabike.seoul

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.goldcompany.apps.koreabike.bikebottomsheet.ShowBikeDataBottomSheet
import com.goldcompany.apps.koreabike.seoulbikedata.SeoulBike
import com.goldcompany.apps.koreabike.seoulbikedata.StationInfo
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay

class SeoulMapViewModel : ViewModel() {
    var bikes1: MutableLiveData<SeoulBike>? = null
    var bikes2: MutableLiveData<SeoulBike>? = null
    var bikes3: MutableLiveData<SeoulBike>? = null

    fun getBikes1(): LiveData<SeoulBike>? {
        bikes1 = SeoulRepository.getBikeData1()
        return bikes1
    }

    fun getBikes2(): LiveData<SeoulBike>? {
        bikes2 = SeoulRepository.getBikeData2()
        return bikes2
    }

    fun getBikes3(): LiveData<SeoulBike>? {
        bikes3 = SeoulRepository.getBikeData3()
        return bikes3
    }
}

class SeoulMapViewModelFactory(): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SeoulMapViewModel() as T
    }
}