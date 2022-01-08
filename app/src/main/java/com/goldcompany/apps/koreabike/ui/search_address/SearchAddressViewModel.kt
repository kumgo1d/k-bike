package com.goldcompany.apps.koreabike.ui.search_address

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goldcompany.apps.koreabike.data.KBikeRepository
import com.goldcompany.apps.koreabike.data.Result
import com.goldcompany.apps.koreabike.data.search_address.Addresses
import com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchAddressViewModel@Inject constructor(
    private val kBikeRepository: KBikeRepository
) : ViewModel() {
    fun setCurrentAddress(userAddress: UserHistoryAddress) {
        viewModelScope.launch {
            val current: UserHistoryAddress? = kBikeRepository.getAddress()
            if(current != null ) updateAddressUnselect(current.date)
            insertAddress(userAddress)
        }
    }

    suspend fun searchAddress(address: String): Flow<Result<Addresses>> = flow {
        emit(kBikeRepository.searchAddress(address))
    }

    private fun insertAddress(userAddress: UserHistoryAddress) {
        viewModelScope.launch {
            kBikeRepository.insertAddress(userAddress)
        }
    }

    private fun updateAddressUnselect(date: Long) {
        viewModelScope.launch {
            kBikeRepository.updateAddressUnselect(date)
        }
    }
}