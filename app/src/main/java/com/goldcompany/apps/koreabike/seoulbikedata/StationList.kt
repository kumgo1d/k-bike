package com.goldcompany.apps.koreabike.seoulbikedata

import com.google.gson.annotations.SerializedName

data class StationList(
    @field:SerializedName("RESULT") val resultCode: RESULT,
    @field:SerializedName("list_total_count") val total: Int,
    @field:SerializedName("row") val stationInfo: List<StationInfo>
)