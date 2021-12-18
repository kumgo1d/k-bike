package com.goldcompany.apps.koreabike.ui.bike_map

import androidx.lifecycle.*
import com.goldcompany.apps.koreabike.data.KBikeRepository
import com.goldcompany.apps.koreabike.data.place_marker.PlaceMarker
import com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class BikeMapViewModel @Inject constructor(
    private val kBikeRepository: KBikeRepository
) : ViewModel() {
    suspend fun searchNearbyPlacesMarker(code: String, longitude: String, latitude: String): Flow<PlaceMarker> = flow {
        emit(kBikeRepository.searchNearbyPlacesMarker(code, longitude, latitude))
    }

    suspend fun getAddress(): Flow<UserHistoryAddress?> = flow {
        emit(kBikeRepository.getAddress())
    }
}