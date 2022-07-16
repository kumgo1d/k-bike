package com.goldcompany.koreabike.data.model.driving

data class Section(
    val congestion: Int,
    val distance: Int,
    val name: String,
    val pointCount: Int,
    val pointIndex: Int,
    val speed: Int
)