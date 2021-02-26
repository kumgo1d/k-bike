package com.goldcompany.apps.koreabike.search_address

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goldcompany.apps.koreabike.KBikeApplication
import com.goldcompany.apps.koreabike.db.item.UserAddress
import kotlinx.coroutines.launch

class SearchAddressViewModel: ViewModel() {
    fun insertAddress(userAddress: UserAddress) {
        viewModelScope.launch {
            KBikeApplication.instance.database.UserAddressDAO().insert(userAddress)
        }
    }
}