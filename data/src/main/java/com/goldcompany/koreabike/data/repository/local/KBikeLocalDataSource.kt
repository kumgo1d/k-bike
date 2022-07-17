package com.goldcompany.koreabike.data.repository.local

import com.goldcompany.koreabike.data.model.address.ApiAddress
import javax.inject.Inject

interface KBikeLocalDataSource {
    suspend fun getAllAddress(): MutableList<ApiAddress>

    suspend fun getAddress(): ApiAddress?

    suspend fun updateAddressUnselect(date: Long)

    suspend fun insertAddress(address: ApiAddress)

    suspend fun deleteAddress(address: ApiAddress)
}

class KBikeLocalDataSourceImpl @Inject constructor() {
}