package com.goldcompany.apps.koreabike.ui.history_place

import androidx.lifecycle.*
import com.goldcompany.apps.koreabike.data.KBikeRepository
import com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryPlaceViewModel @Inject constructor(
    private val kBikeRepository: KBikeRepository
) : ViewModel() {
    private val _addressList = MutableLiveData<MutableList<UserHistoryAddress>?>()
    val addressList: LiveData<MutableList<UserHistoryAddress>?> = _addressList

    init {
        loadAddress()
    }

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

            if (current?.date.toString() == selected.date.toString()) {
                return@launch
            }

            if (current != null) kBikeRepository.updateAddressUnselect(current.date)
            kBikeRepository.insertAddress(address)
        }
    }

    fun deleteAddress(address: UserHistoryAddress) {
        viewModelScope.launch {
            kBikeRepository.deleteAddress(address)
        }
    }

    private fun loadAddress() {
        viewModelScope.launch {
            _addressList.value = kBikeRepository.getAllAddress()
        }
    }
}