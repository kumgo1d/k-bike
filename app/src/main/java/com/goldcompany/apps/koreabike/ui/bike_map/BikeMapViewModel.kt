package com.goldcompany.apps.koreabike.ui.bike_map

import android.graphics.Color
import androidx.lifecycle.*
import com.goldcompany.apps.koreabike.data.KBikeRepository
import com.goldcompany.apps.koreabike.data.Result
import com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.NaverMap
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

    val markers: MutableLiveData<List<Marker>> by lazy {
        MutableLiveData<List<Marker>>()
    }

    suspend fun searchNearbyPlacesMarker(naverMap: NaverMap, code: String, longitude: String, latitude: String) {
        val placeMarkers = mutableListOf<Marker>()
        val result = kBikeRepository.searchNearbyPlacesMarker(code, longitude, latitude)
        if (result is Result.Success) {
            result.data.places.forEach { place ->
                val marker = Marker()
                marker.apply {
                    width = 70
                    height = 100
                    position = LatLng(place.y.toDouble(), place.x.toDouble())

                    when (code) {
                        "PM9" -> {
                            marker.icon = MarkerIcons.BLACK
                            marker.iconTintColor = Color.RED
                        }
                        "CS2" -> {
                            marker.icon = MarkerIcons.BLACK
                            marker.iconTintColor = Color.GREEN
                        }
                        "CE7" -> {
                            marker.icon = MarkerIcons.BLACK
                            marker.iconTintColor = Color.DKGRAY
                        }
                        "AD5" -> {
                            marker.icon = MarkerIcons.BLACK
                            marker.iconTintColor = Color.MAGENTA
                        }
                    }
                    map = naverMap
                }
                placeMarkers.add(marker)
            }

            markers.value = placeMarkers
        }
    }

    suspend fun getAddress(): Flow<UserHistoryAddress?> = flow {
        emit(kBikeRepository.getAddress())
    }
}