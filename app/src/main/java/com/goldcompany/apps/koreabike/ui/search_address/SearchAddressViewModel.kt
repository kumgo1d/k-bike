package com.goldcompany.apps.koreabike.ui.search_address

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.goldcompany.apps.koreabike.KBikeApplication
import com.goldcompany.apps.koreabike.data.KBikeRepository
import com.goldcompany.apps.koreabike.data.kakaodata.KakaoData
import com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress
import com.goldcompany.apps.koreabike.location.LocationProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class SearchAddressViewModel(application: Application): AndroidViewModel(application) {

    private val kBikeRepository =  KBikeRepository.getRepository(application)

    fun setCurrentAddress(userAddress: UserHistoryAddress) {
        viewModelScope.launch {
            val current = LocationProvider.getUserAddress()
            if(current != null) {
                val unSelected = UserHistoryAddress(
                    date = current.date,
                    longitude = current.longitude,
                    latitude = current.latitude,
                    keyword = current.keyword,
                    address = current.address,
                    selected = false
                )
                insertAddress(unSelected)
            }
            insertAddress(userAddress)
        }
    }

    suspend fun getAddress(address: String): Flow<KakaoData> = flow {
        emit(kBikeRepository.getKeywordAddressItem(address))
    }

    private fun insertAddress(userAddress: UserHistoryAddress) {
        viewModelScope.launch {
            KBikeApplication.instance.database.UserAddressDAO().insert(userAddress)
        }
    }
}