package com.goldcompany.koreabike.domain.repository

import com.goldcompany.koreabike.domain.model.Address

interface KBikeRepository {
    suspend fun searchAddress(address: String, page: Int): List<Address>

    suspend fun searchNearbyPlaces(
        code: String,
        longitude: String,
        latitude: String
    ): List<Address>

    suspend fun getAllAddress(): List<Address>

    suspend fun getAddress(): Address?

    suspend fun updateAddressUnselect(date: Long)

    suspend fun insertAddress(address: Address)

    suspend fun deleteAddress(address: Address)
}