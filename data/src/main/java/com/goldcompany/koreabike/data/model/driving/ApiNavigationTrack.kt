package com.goldcompany.koreabike.data.model.driving

data class ApiNavigationTrack(
    val guide: List<Guide>,
    val path: List<List<Double>>,
    val section: List<Section>,
    val summary: Summary
)