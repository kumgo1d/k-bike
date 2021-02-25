package com.goldcompany.apps.koreabike.find_places.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.goldcompany.apps.koreabike.db.KBikeDatabase
import com.goldcompany.apps.koreabike.db.item.UserAddress

class FavoritePlaceViewModel: ViewModel() {
    private val addressList = MutableLiveData<List<UserAddress>?>(null)

    fun getAddressList(): LiveData<List<UserAddress>?> {
        return addressList
    }

    private fun loadAddress() {

    }
}