package com.goldcompany.apps.koreabike.seoul_bike_data

import com.google.gson.annotations.SerializedName

data class StationList(
    @field:SerializedName("list_total_count") val total: Int,
    @field:SerializedName("row") val stationInfo: List<StationInfo>
)