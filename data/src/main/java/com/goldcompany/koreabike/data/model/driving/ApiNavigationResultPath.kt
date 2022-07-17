package com.goldcompany.koreabike.data.model.driving

data class ApiNavigationResultPath(
    val code: Int,
    val currentDateTime: String,
    val message: String,
    val apiNavigationRoute: ApiNavigationRoute?
)