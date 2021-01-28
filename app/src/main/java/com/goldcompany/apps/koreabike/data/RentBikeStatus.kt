package com.goldcompany.apps.koreabike.data

data class RentBikeStatus(
    val RESULT: RESULT,
    val list_total_count: Int,
    val row: List<Row>
)