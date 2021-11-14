package com.goldcompany.apps.koreabike.data.driving

import com.google.gson.annotations.SerializedName

data class Route(
    @SerializedName("tracomfort") val track: List<Track>
)