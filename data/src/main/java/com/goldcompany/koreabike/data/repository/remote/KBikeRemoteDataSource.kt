package com.goldcompany.koreabike.data.repository.remote

import com.goldcompany.koreabike.data.model.address.ApiAddress
import com.goldcompany.koreabike.data.model.address.ApiAddressResult
import com.goldcompany.koreabike.data.model.driving.ApiNavigationResultPath
import com.goldcompany.koreabike.data.model.place.ApiPlaceMarkerResult
import javax.inject.Inject

interface KBikeRemoteDataSource {
    suspend fun searchAddress(address: String, page: Int): ApiAddressResult

    fun searchAddress(address: String): List<ApiAddress>

    suspend fun searchNearbyPlaces(
        code: String,
        longitude: String,
        latitude: String
    ): ApiPlaceMarkerResult

    suspend fun getNavigationPath(start: String, end: String): ApiNavigationResultPath
}

class KBikeRemoteDataSourceImpl @Inject constructor() {

}