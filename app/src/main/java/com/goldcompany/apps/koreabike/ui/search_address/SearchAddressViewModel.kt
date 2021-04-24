package com.goldcompany.apps.koreabike.ui.search_address

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goldcompany.apps.koreabike.KBikeApplication
import com.goldcompany.apps.koreabike.db.item.UserAddress
import com.goldcompany.apps.koreabike.location.LocationProvider
import kotlinx.coroutines.launch

class SearchAddressViewModel: ViewModel() {
    private fun insertAddress(userAddress: UserAddress) {
        viewModelScope.launch {
            KBikeApplication.instance.database.UserAddressDAO().insert(userAddress)
        }
    }

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
}