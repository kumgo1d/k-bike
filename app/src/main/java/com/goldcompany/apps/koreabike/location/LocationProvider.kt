package com.goldcompany.apps.koreabike.location

import com.goldcompany.apps.koreabike.KBikeApplication
import com.goldcompany.apps.koreabike.db.item.UserAddress

object LocationProvider {
    suspend fun getUserAddress(): UserAddress {
        return KBikeApplication.instance.database.UserAddressDAO().getAddress()
    }
}