package com.goldcompany.koreabike.data.repository

import com.goldcompany.koreabike.data.mapper.mapperAddressToUserAddressEntity
import com.goldcompany.koreabike.data.mapper.mapperApiAddressToAddress
import com.goldcompany.koreabike.data.mapper.mapperUserAddressEntityToAddress
import com.goldcompany.koreabike.data.repository.local.KBikeLocalDataSource
import com.goldcompany.koreabike.data.repository.remote.KBikeRemoteDataSource
import com.goldcompany.koreabike.domain.model.Address
import com.goldcompany.koreabike.domain.repository.KBikeRepository

class KBikeRepositoryImpl(
    private val localDataSource: KBikeLocalDataSource,
    private val remoteDataSource: KBikeRemoteDataSource
) : KBikeRepository {
    override suspend fun searchAddress(address: String, page: Int): List<Address> {
        return remoteDataSource.searchAddress(address, page).addressList.map {
            mapperApiAddressToAddress(it)
        }
    }

    override suspend fun searchNearbyPlaces(
        code: String,
        longitude: String,
        latitude: String
    ): List<Address> {
        return remoteDataSource.searchNearbyPlaces(code, longitude, latitude).apiPlaces.map {
            mapperApiAddressToAddress(it)
        }
    }

    override suspend fun getNavigationPath(start: String, end: String) {

    }

    override suspend fun getAllAddress(): List<Address> {
        return localDataSource.getAllAddress().map {
            mapperUserAddressEntityToAddress(it)
        }
    }

    override suspend fun getAddress(): Address? {
        return localDataSource.getAddress()?.let { mapperUserAddressEntityToAddress(it) }
    }

    override suspend fun updateCurrentAddressUnselected(id: String) {
        localDataSource.updateCurrentAddressUnselected(id)
    }

    override suspend fun insertAddress(address: Address) {
        localDataSource.insertAddress(mapperAddressToUserAddressEntity(address))
    }

    override suspend fun deleteAddress(address: Address) {
        localDataSource.deleteAddress(mapperAddressToUserAddressEntity(address))
    }
}