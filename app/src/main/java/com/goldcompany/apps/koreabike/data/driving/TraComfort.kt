package com.goldcompany.apps.koreabike.data.driving

data class TraComfort(
    val guide: List<Guide>,
    val path: List<List<Double>>,
    val section: List<Section>,
    val summary: Summary
)