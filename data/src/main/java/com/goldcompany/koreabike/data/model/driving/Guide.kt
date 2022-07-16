package com.goldcompany.koreabike.data.model.driving

data class Guide(
    val distance: Int,
    val duration: Int,
    val instructions: String,
    val pointIndex: Int,
    val type: Int
)