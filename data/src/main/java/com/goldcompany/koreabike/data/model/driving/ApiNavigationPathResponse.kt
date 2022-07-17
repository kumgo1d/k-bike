package com.goldcompany.koreabike.data.model.driving

data class ApiNavigationPathResponse(
    val code: Int,
    val currentDateTime: String,
    val message: String,
    val apiNavigationRoute: ApiNavigationRoute?
)