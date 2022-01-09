package com.goldcompany.apps.koreabike.ui.search_address

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goldcompany.apps.koreabike.data.KBikeRepository
import com.goldcompany.apps.koreabike.data.Result
import com.goldcompany.apps.koreabike.data.search_address.Addresses
import com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchAddressViewModel@Inject constructor(
    private val kBikeRepository: KBikeRepository
) : ViewModel() {

    private val _searchAddresses = MutableLiveData<Addresses?>()
    val searchAddresses: LiveData<Addresses?> = _searchAddresses

    suspend fun searchAddress(address: String)  {
        val result = kBikeRepository.searchAddress(address)
        if(result is Result.Success) {
            _searchAddresses.value = result.data
        }
    }

    fun setCurrentAddress(userAddress: UserHistoryAddress) {
        viewModelScope.launch {
            val current: UserHistoryAddress? = kBikeRepository.getAddress()
            if(current != null ) updateAddressUnselect(current.date)
            insertAddress(userAddress)
        }
    }

    private fun updateAddressUnselect(date: Long) {
        viewModelScope.launch {
            kBikeRepository.updateAddressUnselect(date)
        }
    }

    private fun insertAddress(userAddress: UserHistoryAddress) {
        viewModelScope.launch {
            kBikeRepository.insertAddress(userAddress)
        }
    }
}