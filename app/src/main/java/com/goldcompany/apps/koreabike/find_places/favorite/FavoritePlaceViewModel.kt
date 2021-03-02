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

    fun setCurrentAddress(address: UserAddress) {
        viewModelScope.launch {
            val dao = KBikeApplication.instance.database.UserAddressDAO()
            val current = dao.getAddress()

            if(current.date == address.date) {
                return@launch
            }

            val unSelected = UserAddress(
                date = current.date,
                longitude = current.longitude,
                latitude = current.latitude,
                address = current.address,
                keyword = current.keyword,
                selected = false
            )

            dao.insert(unSelected)
            dao.insert(address)
        }
    }
}