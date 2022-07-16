package com.goldcompany.apps.koreabike.data.driving

data class ResultPath(
    val code: Int,
    val currentDateTime: String,
    val message: String,
    val route: Route?
)