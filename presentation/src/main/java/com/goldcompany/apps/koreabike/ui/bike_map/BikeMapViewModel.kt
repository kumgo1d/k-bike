package com.goldcompany.apps.koreabike.ui.bike_map

import android.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.goldcompany.apps.koreabike.data.KBikeRepository
import com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress
import com.goldcompany.apps.koreabike.util.Result
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.MarkerIcons
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class BikeMapViewModel @Inject constructor(
    private val kBikeRepository: KBikeRepository
) : ViewModel() {

    val markers by lazy { MutableLiveData<List<Marker>>() }
    val isMarked by lazy { MutableLiveData<Boolean>() }

    init {
        isMarked.postValue(false)
    }

    suspend fun searchNearbyPlacesMarker(code: String, longitude: String, latitude: String) {
        val placeMarkers = mutableListOf<Marker>()
        val result = kBikeRepository.searchNearbyPlacesMarker(code, longitude, latitude)
        if (result is Result.Success) {
            result.data.apiPlaces.forEach { place ->
                val marker = Marker()
                marker.apply {
                    width = MARKER_WIDTH
                    height = MARKER_HEIGHT
                    position = LatLng(place.y.toDouble(), place.x.toDouble())

                    when (code) {
                        PHARMACY -> {
                            marker.icon = MarkerIcons.BLACK
                            marker.iconTintColor = Color.RED
                        }
                        CONVENIENCE_STORE -> {
                            marker.icon = MarkerIcons.BLACK
                            marker.iconTintColor = Color.GREEN
                        }
                        CAFE -> {
                            marker.icon = MarkerIcons.BLACK
                            marker.iconTintColor = Color.DKGRAY
                        }
                        ACCOMMODATION -> {
                            marker.icon = MarkerIcons.BLACK
                            marker.iconTintColor = Color.MAGENTA
                        }
                    }
                }
                placeMarkers.add(marker)
            }

            markers.value = placeMarkers
        }
    }

    suspend fun getAddress(): Flow<UserHistoryAddress?> = flow {
        emit(kBikeRepository.getAddress())
    }

    companion object {
        private const val MARKER_WIDTH = 70
        private const val MARKER_HEIGHT = 100
        private const val PHARMACY = "PM9"
        private const val CONVENIENCE_STORE = "CS2"
        private const val CAFE = "CE7"
        private const val ACCOMMODATION = "AD5"
    }
}