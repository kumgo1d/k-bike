package com.goldcompany.apps.koreabike.data

import androidx.paging.PagingData
import com.goldcompany.apps.koreabike.data.driving.ResultPath
import com.goldcompany.apps.koreabike.data.place_marker.PlaceMarker
import com.goldcompany.apps.koreabike.data.search_address.AddressItem
import com.goldcompany.apps.koreabike.data.search_address.Addresses
import com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress
import com.goldcompany.apps.koreabike.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KBikeRepository @Inject constructor(
    private val kBikeRemoteDataSource: KBikeRemoteDataSource,
    private val kBikeLocalDataSource: KBikeLocalDataSource
) {

    suspend fun searchAddress(
        address:String,
        page: Int
    ): Addresses = kBikeRemoteDataSource.searchAddress(address, page)

    fun getSearchAddressStream(
        address: String
    ): Flow<PagingData<AddressItem>> = kBikeRemoteDataSource.getSearchAddressStream(address)

    suspend fun searchNearbyPlacesMarker(
        code: String,
        longitude: String,
        latitude: String
    ): Result<PlaceMarker> = kBikeRemoteDataSource.searchNearbyPlacesMarker(code, longitude, latitude)

    suspend fun getNavigationPath(
        start: String,
        end: String
    ): Result<ResultPath> = kBikeRemoteDataSource.getNavigationPath(start, end)

    suspend fun getAllAddress(): MutableList<UserHistoryAddress> = kBikeLocalDataSource.getAllAddress()

    suspend fun getAddress(): UserHistoryAddress? = kBikeLocalDataSource.getAddress()

    suspend fun updateAddressUnselect(date: Long) = kBikeLocalDataSource.updateAddressUnselect(date)

    suspend fun insertAddress(address: UserHistoryAddress) = kBikeLocalDataSource.insertAddress(address)

    suspend fun deleteAddress(address: UserHistoryAddress) = kBikeLocalDataSource.deleteAddress(address)
}