package com.goldcompany.apps.koreabike.seoulbikedata

data class StationInfo(
    val parkingBikeTotCnt: String,
    val rackTotCnt: String,
    val shared: String,
    val stationDt: String,
    val stationId: String,
    val stationLatitude: String,
    val stationLongitude: String,
    val stationName: String
)