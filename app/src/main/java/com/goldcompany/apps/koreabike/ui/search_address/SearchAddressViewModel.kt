package com.goldcompany.apps.koreabike.ui.search_address

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goldcompany.apps.koreabike.KBikeApplication
import com.goldcompany.apps.koreabike.data.KBikeRepository
import com.goldcompany.apps.koreabike.data.kakaodata.KakaoData
import com.goldcompany.apps.koreabike.db.item.UserAddress
import com.goldcompany.apps.koreabike.location.LocationProvider
import kotlinx.coroutines.launch
import java.lang.Exception

class SearchAddressViewModel(application: Application): AndroidViewModel(application) {

    private val kBikeRepository = KBikeRepository.getRepository(application)

    fun setCurrentAddress(userAddress: UserAddress) {
        viewModelScope.launch {
            val current = LocationProvider.getUserAddress()
            if(current != null) {
                val unSelected = UserAddress(
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

    fun getAddress(address: String, onComplete: (KakaoData?, Exception?) -> Unit) {
        return kBikeRepository.getKeywordAddressItem(address, onComplete)
    }

    private fun insertAddress(userAddress: UserAddress) {
        viewModelScope.launch {
            KBikeApplication.instance.database.UserAddressDAO().insert(userAddress)
        }
    }
}