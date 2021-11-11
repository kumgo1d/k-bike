package com.goldcompany.apps.koreabike.ui.history_place

import android.app.Application
import androidx.lifecycle.*
import com.goldcompany.apps.koreabike.data.KBikeRepository
import com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress
import kotlinx.coroutines.launch

class HistoryPlaceViewModel(application: Application): AndroidViewModel(application) {
    private val addressList = MutableLiveData<MutableList<UserHistoryAddress>?>()
    private val kBikeRepository =  KBikeRepository.getRepository(application)

    fun setCurrentAddress(address: UserHistoryAddress) {
        viewModelScope.launch {
            val current = kBikeRepository.getAddress()
            val selected = UserHistoryAddress(
                date = System.currentTimeMillis(),
                longitude = address.longitude,
                latitude = address.latitude,
                address = address.address,
                keyword = address.keyword,
                selected = true
            )

            if(current.date == selected.date) {
                return@launch
            }

            val unSelected = UserHistoryAddress(
                date = current.date,
                longitude = current.longitude,
                latitude = current.latitude,
                address = current.address,
                keyword = current.keyword,
                selected = false
            )

            kBikeRepository.insertAddress(unSelected)
            kBikeRepository.insertAddress(selected)
        }
    }

    fun deleteAddress(address: UserHistoryAddress) {
        viewModelScope.launch {
            kBikeRepository.deleteAddress(address)
        }
    }

    fun getAddress(): LiveData<MutableList<UserHistoryAddress>?> {
        loadAddress()
        return addressList
    }

    private fun loadAddress() {
        viewModelScope.launch {
            addressList.value = kBikeRepository.getAllAddress()
        }
    }
}