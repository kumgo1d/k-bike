package com.goldcompany.apps.koreabike.data.driving

import com.goldcompany.koreabike.data.model.driving.ApiNavigationTrack
import com.google.gson.annotations.SerializedName

data class Route(
    @SerializedName("tracomfort") val comfort: List<ApiNavigationTrack>
//    @SerializedName("traoptimal") val optimal: List<Track>,
//    @SerializedName("trafast") val fast: List<Track>
)