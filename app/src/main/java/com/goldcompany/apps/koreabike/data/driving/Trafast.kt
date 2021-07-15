package com.goldcompany.apps.koreabike.data.driving

data class Trafast(
    val guide: List<Guide>,
    val path: List<List<Double>>,
    val section: List<Section>,
    val summary: Summary
)