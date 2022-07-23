package com.goldcompany.apps.koreabike.ui.history_place

import androidx.lifecycle.*
import com.goldcompany.apps.koreabike.data.KBikeRepository
import com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress
import com.goldcompany.koreabike.domain.model.Address
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryPlaceViewModel @Inject constructor(
    private val kBikeRepository: KBikeRepository
) : ViewModel() {

    private val _addressList = liveData<List<Address>> {
        emit(kBikeRepository.getAllAddress())
    }
    val addressList: LiveData<List<Address>> = _addressList

    fun setCurrentAddress(address: Address) {
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

    fun deleteAddress(address: Address) {
        viewModelScope.launch {
            kBikeRepository.deleteAddress(address)
        }
    }
}