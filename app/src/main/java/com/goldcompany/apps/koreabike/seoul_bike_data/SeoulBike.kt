package com.goldcompany.apps.koreabike.seoul_bike_data

import com.google.gson.annotations.SerializedName

data class SeoulBike(
    @field:SerializedName("getStationListHist") val stationList: StationList
)