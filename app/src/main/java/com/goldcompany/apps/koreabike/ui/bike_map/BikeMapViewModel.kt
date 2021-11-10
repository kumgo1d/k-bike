package com.goldcompany.apps.koreabike.ui.bike_map

import android.app.Application
import androidx.lifecycle.*
import com.goldcompany.apps.koreabike.data.KBikeRepository
import com.goldcompany.apps.koreabike.data.place_marker.PlaceMarker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BikeMapViewModel(application: Application) : AndroidViewModel(application) {
    private val kBikeRepository =  KBikeRepository.getRepository(application)

    suspend fun searchNearbyPlacesMarker(code: String, longitude: String, latitude: String): Flow<PlaceMarker> = flow {
        emit(kBikeRepository.searchNearbyPlacesMarker(code, longitude, latitude))
    }
}