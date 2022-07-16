package com.goldcompany.apps.koreabike.data

import androidx.paging.PagingData
import com.goldcompany.koreabike.data.model.driving.ApiNavigationResultPath
import com.goldcompany.koreabike.data.model.place.PlaceMarkerMetaData
import com.goldcompany.koreabike.data.model.place.ApiPlaceMarkerResult
import com.goldcompany.koreabike.data.model.address.ApiAddress
import com.goldcompany.koreabike.data.model.address.AddressMetaData
import com.goldcompany.koreabike.data.model.address.ApiAddressResult
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

    override suspend fun searchAddress(address: String, page: Int): ApiAddressResult {
        return ApiAddressResult(listOf(), AddressMetaData(false, 9999, 9999))
    }

    override fun getSearchAddressStream(address: String): Flow<PagingData<ApiAddress>> {
        return flow {  }
    }

    override suspend fun searchNearbyPlacesMarker(
        code: String,
        longitude: String,
        latitude: String
    ): Result<ApiPlaceMarkerResult> {
        return if (networkError) {
            Result.Error(Exception("network error"))
        } else {
            Result.Success(
                ApiPlaceMarkerResult(listOf(), PlaceMarkerMetaData(false, 9999, 9999))
            )
        }
    }

    override suspend fun getNavigationPath(start: String, end: String): Resource<ApiNavigationResultPath> {
        return if (networkError) {
            Resource.error("Network Error", null)
        } else {
            Resource.success(ApiNavigationResultPath(9999, "", "길찾기를 성공하였습니다.", null))
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