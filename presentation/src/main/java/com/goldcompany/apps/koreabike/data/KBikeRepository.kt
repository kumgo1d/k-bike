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
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KBikeRepository @Inject constructor(
    private val kBikeRemoteDataSource: KBikeRemoteDataSource,
    private val kBikeLocalDataSource: KBikeLocalDataSource
) : BaseRepository {
    override suspend fun searchAddress(
        address:String,
        page: Int
    ): ApiAddressResponse = kBikeRemoteDataSource.searchAddress(address, page)

    override fun getSearchAddressStream(
        address: String
    ): Flow<PagingData<ApiAddress>> = kBikeRemoteDataSource.getSearchAddressStream(address)

    override suspend fun searchNearbyPlacesMarker(
        code: String,
        longitude: String,
        latitude: String
    ): Result<ApiPlaceMarkerResponse> = kBikeRemoteDataSource.searchNearbyPlacesMarker(code, longitude, latitude)

    override suspend fun getNavigationPath(
        start: String,
        end: String
    ): Resource<ApiNavigationPathResponse> = kBikeRemoteDataSource.getNavigationPath(start, end)

    override suspend fun getAllAddress(): MutableList<UserHistoryAddress> = kBikeLocalDataSource.getAllAddress()

    override suspend fun getAddress(): UserHistoryAddress? = kBikeLocalDataSource.getAddress()

    override suspend fun updateAddressUnselect(date: Long) = kBikeLocalDataSource.updateAddressUnselect(date)

    override suspend fun insertAddress(address: UserHistoryAddress) = kBikeLocalDataSource.insertAddress(address)

    override suspend fun deleteAddress(address: UserHistoryAddress) = kBikeLocalDataSource.deleteAddress(address)
}