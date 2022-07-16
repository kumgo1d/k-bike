package com.goldcompany.apps.koreabike.data.driving

import com.goldcompany.koreabike.data.model.driving.ApiNavigationRoute

data class ResultPath(
    val code: Int,
    val currentDateTime: String,
    val message: String,
    val apiNavigationRoute: ApiNavigationRoute?
)