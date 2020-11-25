package com.android.apps.seoulpublicbike.data

data class RentBikeStatus(
    val RESULT: RESULT,
    val list_total_count: Int,
    val row: List<Row>
)