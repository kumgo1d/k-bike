package com.goldcompany.apps.koreabike.find_places.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goldcompany.apps.koreabike.KBikeApplication
import com.goldcompany.apps.koreabike.db.item.UserAddress
import kotlinx.coroutines.launch

class FavoritePlaceViewModel: ViewModel() {
    private val addressList = MutableLiveData<MutableList<UserAddress>?>()

    fun getAddress(): LiveData<MutableList<UserAddress>?> {
        loadAddress()
        return addressList
    }

    private fun loadAddress() {
        viewModelScope.launch {
            addressList.value = KBikeApplication.instance.database.UserAddressDAO().getAll()
        }
    }
}