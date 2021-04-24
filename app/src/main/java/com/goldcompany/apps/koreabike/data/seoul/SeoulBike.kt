package com.goldcompany.apps.koreabike.data.seoul

import com.google.gson.annotations.SerializedName

data class SeoulBike(
    @field:SerializedName("rentBikeStatus") val stationList: StationList
)