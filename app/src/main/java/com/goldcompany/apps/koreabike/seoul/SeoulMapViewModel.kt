package com.goldcompany.apps.koreabike.seoul

import androidx.lifecycle.*
import com.goldcompany.apps.koreabike.db.item.UserAddress
import com.goldcompany.apps.koreabike.location.LocationProvider
import com.goldcompany.apps.koreabike.seoul.seoul_api.SeoulRepository
import com.goldcompany.apps.koreabike.seoul_bike_data.SeoulBike
import kotlinx.coroutines.launch

class SeoulMapViewModel : ViewModel() {
    private var bikes1 = MutableLiveData<SeoulBike>()
    private var bikes2 = MutableLiveData<SeoulBike>()
    private var bikes3 = MutableLiveData<SeoulBike>()

    private var recentAddress: UserAddress? = null

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

    fun getRecentAddress(): UserAddress? {
        loadUserAddress()
        return recentAddress
    }

    private fun loadUserAddress() {
        viewModelScope.launch {
            recentAddress = LocationProvider.getUserAddress()
        }
    }
}

class SeoulMapViewModelFactory(): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SeoulMapViewModel() as T
    }
}