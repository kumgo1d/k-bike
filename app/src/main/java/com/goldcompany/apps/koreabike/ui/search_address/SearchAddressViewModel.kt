package com.goldcompany.apps.koreabike.ui.search_address

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.goldcompany.apps.koreabike.data.KBikeRepository
import com.goldcompany.apps.koreabike.data.search_address.AddressItem
import com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchAddressViewModel@Inject constructor(
    private val kBikeRepository: KBikeRepository
) : ViewModel() {

    private var currentQuery: String? = null
    private var currentSearchResult: Flow<PagingData<AddressItem>>? = null

    fun searchAddress(address: String): Flow<PagingData<AddressItem>> {
        val lastResult = currentSearchResult
        if (address == currentQuery && lastResult != null) {
            return lastResult
        }
        currentQuery = address
        val newResult: Flow<PagingData<AddressItem>> = kBikeRepository.getSearchAddressStream(address)
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
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