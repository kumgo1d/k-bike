package com.goldcompany.koreabike.data.repository

import com.goldcompany.koreabike.domain.model.Address
import com.goldcompany.koreabike.domain.repository.KBikeRepository

class KBikeRepositoryImpl : KBikeRepository {
    override fun searchAddress(address: String, page: Int): List<Address> {
        TODO("Not yet implemented")
    }

    override suspend fun searchNearbyPlacesMarker(
        code: String,
        longitude: String,
        latitude: String
    ): List<Address> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllAddress(): MutableList<Address> {
        TODO("Not yet implemented")
    }

    override suspend fun getAddress(): Address? {
        TODO("Not yet implemented")
    }

    override suspend fun updateAddressUnselect(date: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun insertAddress(address: Address) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAddress(address: Address) {
        TODO("Not yet implemented")
    }
}