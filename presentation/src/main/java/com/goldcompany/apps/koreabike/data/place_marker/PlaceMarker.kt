package com.goldcompany.apps.koreabike.data.place_marker

import com.google.gson.annotations.SerializedName

data class PlaceMarker(
    @SerializedName("documents") val places: List<Place>,
    val meta: PlaceMarkerMetaData
)