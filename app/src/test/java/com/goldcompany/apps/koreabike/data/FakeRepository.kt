package com.goldcompany.apps.koreabike.data

import androidx.paging.PagingData
import com.goldcompany.apps.koreabike.data.driving.ResultPath
import com.goldcompany.apps.koreabike.data.place_marker.PlaceMarkerMetaData
import com.goldcompany.apps.koreabike.data.place_marker.PlaceMarker
import com.goldcompany.apps.koreabike.data.search_address.AddressItem
import com.goldcompany.apps.koreabike.data.search_address.AddressMetaData
import com.goldcompany.apps.koreabike.data.search_address.Addresses
import com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress
import com.goldcompany.apps.koreabike.util.Resource
import com.goldcompany.apps.koreabike.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepository: BaseRepository  {

    private val addressList = mutableListOf<UserHistoryAddress>()

    private var networkError = false
        set(value: Boolean) {
            field = value
        }

    override suspend fun searchAddress(address: String, page: Int): Addresses {
        return Addresses(listOf(), AddressMetaData(false, 9999, 9999))
    }

    override fun getSearchAddressStream(address: String): Flow<PagingData<AddressItem>> {
        return flow {  }
    }

    override suspend fun searchNearbyPlacesMarker(
        code: String,
        longitude: String,
        latitude: String
    ): Result<PlaceMarker> {
        return if (networkError) {
            Result.Error(Exception("network error"))
        } else {
            Result.Success(
                PlaceMarker(listOf(), PlaceMarkerMetaData(false, 9999, 9999))
            )
        }
    }

    override suspend fun getNavigationPath(start: String, end: String): Resource<ResultPath> {
        return if (networkError) {
            Resource.error("Network Error", null)
        } else {
            Resource.success(ResultPath(9999, "", "success", null))
        }
    }

    override suspend fun getAllAddress(): MutableList<UserHistoryAddress> {
        return addressList
    }

    override suspend fun getAddress(): UserHistoryAddress? {
        return null
    }

    override suspend fun updateAddressUnselect(date: Long) {}

    override suspend fun insertAddress(address: UserHistoryAddress) {
        addressList.add(address)
    }

    override suspend fun deleteAddress(address: UserHistoryAddress) {
        addressList.remove(address)
    }

}