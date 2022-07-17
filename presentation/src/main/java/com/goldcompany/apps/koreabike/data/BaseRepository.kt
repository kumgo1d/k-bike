package com.goldcompany.apps.koreabike.data

import androidx.paging.PagingData
import com.goldcompany.koreabike.data.model.driving.ApiNavigationPathResponse
import com.goldcompany.koreabike.data.model.place.ApiPlaceMarkerResponse
import com.goldcompany.koreabike.data.model.address.ApiAddress
import com.goldcompany.koreabike.data.model.address.ApiAddressResponse
import com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress
import com.goldcompany.apps.koreabike.util.Resource
import com.goldcompany.apps.koreabike.util.Result
import kotlinx.coroutines.flow.Flow

interface BaseRepository {
    suspend fun searchAddress(address: String, page: Int): ApiAddressResponse

    fun getSearchAddressStream(address: String): Flow<PagingData<ApiAddress>>

    suspend fun searchNearbyPlacesMarker(
        code: String,
        longitude: String,
        latitude: String
    ): Result<ApiPlaceMarkerResponse>

    suspend fun getNavigationPath(
        start: String,
        end: String
    ): Resource<ApiNavigationPathResponse>

    suspend fun getAllAddress(): MutableList<UserHistoryAddress>

    suspend fun getAddress(): UserHistoryAddress?

    suspend fun updateAddressUnselect(date: Long)

    suspend fun insertAddress(address: UserHistoryAddress)

    suspend fun deleteAddress(address: UserHistoryAddress)

}