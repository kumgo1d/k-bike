package com.goldcompany.koreabike.data.repository

import com.goldcompany.koreabike.data.mapper.*
import com.goldcompany.koreabike.data.repository.local.KBikeLocalDataSource
import com.goldcompany.koreabike.data.repository.remote.KBikeRemoteDataSource
import com.goldcompany.koreabike.domain.model.Result
import com.goldcompany.koreabike.domain.model.address.Address
import com.goldcompany.koreabike.domain.model.navigation.Navigation
import com.goldcompany.koreabike.domain.repository.KBikeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class KBikeRepositoryImpl(
    private val localDataSource: KBikeLocalDataSource,
    private val remoteDataSource: KBikeRemoteDataSource
) : KBikeRepository {
    override suspend fun searchAddress(address: String, page: Int): Result<List<Address>> = withContext(Dispatchers.IO) {
        val response = remoteDataSource.searchAddress(address, page)

        return@withContext Result.Success(
            response.addressList.map {
                mapperApiAddressToAddress(it)
            }
        )
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

    override suspend fun getNavigationPath(start: String, end: String): Navigation {
        return mapperApiRouteToNavigation(remoteDataSource.getNavigationPath(start, end).apiNavigationRoute.comfort)
    }

    override fun getAllAddress(): Flow<Result<List<Address>>> {
        return localDataSource.getAllAddress().map {
            Result.Success(mapperAddressEntityListToAddressList(it))
        }
    }

    override suspend fun getAddress(): Result<Address?> = withContext(Dispatchers.IO) {
        val address = localDataSource.getAddress()
        return@withContext if (address != null) { Result.Success(mapperUserAddressEntityToAddress(address)) }
        else { Result.Error(Exception("no local address"))}
    }

    override suspend fun updateCurrentAddressUnselected(id: String) {
        localDataSource.updateCurrentAddressUnselected(id)
    }

    override suspend fun insertAddress(address: Address) {
        localDataSource.insertAddress(mapperAddressToUserAddressEntity(address))
    }

    override suspend fun deleteAddress(address: Address) {
        val newAddress = mapperAddressToUserAddressEntity(address)
        newAddress.selected = true
        localDataSource.deleteAddress(newAddress)
    }
}