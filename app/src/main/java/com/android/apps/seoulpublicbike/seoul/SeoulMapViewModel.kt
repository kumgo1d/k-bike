package com.android.apps.seoulpublicbike.seoul

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.apps.seoulpublicbike.data.Bike

class SeoulMapViewModel : ViewModel() {
    var bikes1: MutableLiveData<Bike>? = null
    var bikes2: MutableLiveData<Bike>? = null
    var bikes3: MutableLiveData<Bike>? = null

    fun getBikes1(): LiveData<Bike>? {
        bikes1 = SeoulRepository.getBikeData1()
        return bikes1
    }
    fun getBikes2(): LiveData<Bike>? {
        bikes2 = SeoulRepository.getBikeData2()
        return bikes2
    }fun getBikes3(): LiveData<Bike>? {
        bikes3 = SeoulRepository.getBikeData3()
        return bikes3
    }

}

class SeoulMapViewModelFactory(): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SeoulMapViewModel() as T
    }
}