package com.goldcompany.apps.koreabike.data.place_marker

import com.goldcompany.koreabike.data.model.place.ApiPlace
import com.goldcompany.koreabike.data.model.place.PlaceMarkerMetaData
import com.google.gson.annotations.SerializedName

data class PlaceMarker(
    @SerializedName("documents") val apiPlaces: List<ApiPlace>,
    val meta: PlaceMarkerMetaData
)