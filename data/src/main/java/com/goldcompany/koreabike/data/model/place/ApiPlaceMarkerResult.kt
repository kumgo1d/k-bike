package com.goldcompany.koreabike.data.model.place

import com.google.gson.annotations.SerializedName

data class ApiPlaceMarkerResult(
    @SerializedName("documents") val apiPlaces: List<ApiPlace>,
    val meta: PlaceMarkerMetaData
)