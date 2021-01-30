package com.goldcompany.apps.koreabike.seoulbikedata

data class GetStationListHist(
    val RESULT: RESULT,
    val list_total_count: Int,
    val row: List<Row>
)