package com.goldcompany.koreabike.data.repository.local

import com.goldcompany.koreabike.data.db.AddressDAO
import com.goldcompany.koreabike.data.model.address.ApiAddress

interface KBikeLocalDataSource {
    suspend fun getAllAddress(): MutableList<ApiAddress>

    suspend fun getAddress(): ApiAddress?

    suspend fun updateAddressUnselect(date: Long)

    suspend fun insertAddress(address: ApiAddress)

    suspend fun deleteAddress(address: ApiAddress)
}

class KBikeLocalDataSourceImpl(private val addressDAO: AddressDAO): KBikeLocalDataSource {
    override suspend fun getAllAddress(): MutableList<ApiAddress> {
        TODO("Not yet implemented")
    }

    override suspend fun getAddress(): ApiAddress? {
        TODO("Not yet implemented")
    }

    override suspend fun updateAddressUnselect(date: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun insertAddress(address: ApiAddress) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAddress(address: ApiAddress) {
        TODO("Not yet implemented")
    }

}