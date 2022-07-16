package com.goldcompany.apps.koreabike.data.driving

data class Summary(
    val bbox: List<List<Double>>,
    val distance: Int,
    val duration: Int,
    val fuelPrice: Int,
    val goal: Goal,
    val start: Start,
    val taxiFare: Int,
    val tollFare: Int
)