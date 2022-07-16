package com.goldcompany.apps.koreabike.data

import androidx.paging.PagingData
import com.goldcompany.apps.koreabike.data.driving.ResultPath
import com.goldcompany.apps.koreabike.data.place_marker.PlaceMarker
import com.goldcompany.apps.koreabike.data.search_address.AddressItem
import com.goldcompany.apps.koreabike.data.search_address.Addresses
import com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress
import com.goldcompany.apps.koreabike.util.Resource
import com.goldcompany.apps.koreabike.util.Result
import kotlinx.coroutines.flow.Flow

interface BaseRepository {
    suspend fun searchAddress(address: String, page: Int): Addresses

    fun getSearchAddressStream(address: String): Flow<PagingData<AddressItem>>

    suspend fun searchNearbyPlacesMarker(
        code: String,
        longitude: String,
        latitude: String
    ): Result<PlaceMarker>

    suspend fun getNavigationPath(
        start: String,
        end: String
    ): Resource<ResultPath>

    suspend fun getAllAddress(): MutableList<UserHistoryAddress>

    suspend fun getAddress(): UserHistoryAddress?

    suspend fun updateAddressUnselect(date: Long)

    suspend fun insertAddress(address: UserHistoryAddress)

    suspend fun deleteAddress(address: UserHistoryAddress)

}