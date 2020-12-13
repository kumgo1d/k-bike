package com.android.apps.seoulpublicbike

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.apps.seoulpublicbike.data.Bike

class SeoulMapViewModel : ViewModel() {
    var bikes: MutableLiveData<Bike>? = null

    fun getBikes(): LiveData<Bike>? {
        bikes = SeoulRepository.getBikeData()
        return bikes
    }
}

class SeoulMapViewModelFactory(): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SeoulMapViewModel() as T
    }
}