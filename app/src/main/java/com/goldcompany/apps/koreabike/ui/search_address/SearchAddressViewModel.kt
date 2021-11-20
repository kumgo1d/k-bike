package com.goldcompany.apps.koreabike.ui.search_address

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.goldcompany.apps.koreabike.data.KBikeRepository
import com.goldcompany.apps.koreabike.data.search_address.Addresses
import com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class SearchAddressViewModel(application: Application): AndroidViewModel(application) {

    private val kBikeRepository =  KBikeRepository.getRepository(application)

    fun setCurrentAddress(userAddress: UserHistoryAddress) {
        viewModelScope.launch {
            val current = kBikeRepository.getAddress()
            updateAddressUnselect(current.date)
            insertAddress(userAddress)
        }
    }

    suspend fun searchAddress(address: String): Flow<Addresses> = flow {
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