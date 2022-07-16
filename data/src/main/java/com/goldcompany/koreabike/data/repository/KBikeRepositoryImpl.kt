package com.goldcompany.koreabike.data.repository

import com.goldcompany.koreabike.data.repository.local.KBikeLocalDataSource
import com.goldcompany.koreabike.data.repository.remote.KBikeRemoteDataSource
import com.goldcompany.koreabike.domain.model.Address
import com.goldcompany.koreabike.domain.repository.KBikeRepository

class KBikeRepositoryImpl(
    private val localDataSource: KBikeLocalDataSource,
    private val remoteDataSource: KBikeRemoteDataSource
) : KBikeRepository {
    override fun searchAddress(address: String, page: Int): List<Address> {
        TODO("Not yet implemented")
    }

    override suspend fun searchNearbyPlaces(
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