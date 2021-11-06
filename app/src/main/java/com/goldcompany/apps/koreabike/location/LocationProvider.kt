package com.goldcompany.apps.koreabike.location

import com.goldcompany.apps.koreabike.KBikeApplication
import com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress

object LocationProvider {
    suspend fun getUserAddress(): UserHistoryAddress? {
        return KBikeApplication.instance.database.UserAddressDAO().getAddress()
    }
}