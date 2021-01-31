package com.goldcompany.apps.koreabike.seoulbikedata

import com.google.gson.annotations.SerializedName

data class SeoulBike(
    @field:SerializedName("getStationListHist") val stationList: StationList
)