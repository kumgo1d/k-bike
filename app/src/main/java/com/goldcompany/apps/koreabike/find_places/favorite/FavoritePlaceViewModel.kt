package com.goldcompany.apps.koreabike.find_places.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goldcompany.apps.koreabike.KBikeApplication
import com.goldcompany.apps.koreabike.db.item.UserAddress
import kotlinx.coroutines.launch

class FavoritePlaceViewModel: ViewModel() {
    private val addressList = MutableLiveData<List<UserAddress>?>(null)

    fun getAddress(): LiveData<List<UserAddress>?> {
        loadAddress()
        return addressList
    }

    private fun loadAddress() {
        viewModelScope.launch {
            addressList.value = KBikeApplication.instance.database.UserAddressDAO().getAddress()
        }
    }
}