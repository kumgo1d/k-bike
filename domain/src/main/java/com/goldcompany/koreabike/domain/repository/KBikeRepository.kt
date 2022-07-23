package com.goldcompany.koreabike.domain.repository

import com.goldcompany.koreabike.domain.model.Address
import com.goldcompany.koreabike.domain.model.navigation.Navigation
import javax.inject.Singleton

@Singleton
interface KBikeRepository {
    suspend fun searchAddress(address: String, page: Int): List<Address>

    suspend fun searchNearbyPlaces(
        code: String,
        longitude: String,
        latitude: String
    ): List<Address>

    suspend fun getNavigationPath(
        start: String,
        end: String
    ): Navigation

    suspend fun getAllAddress(): List<Address>

    suspend fun getAddress(): Address?

    suspend fun updateCurrentAddressUnselected(id: String)

    suspend fun insertAddress(address: Address)

    suspend fun deleteAddress(address: Address)
}