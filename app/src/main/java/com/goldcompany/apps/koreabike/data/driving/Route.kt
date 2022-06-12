package com.goldcompany.apps.koreabike.data.driving

import com.google.gson.annotations.SerializedName

data class Route(
    @SerializedName("tracomfort") val comfort: List<Track>
//    @SerializedName("traoptimal") val optimal: List<Track>,
//    @SerializedName("trafast") val fast: List<Track>
)