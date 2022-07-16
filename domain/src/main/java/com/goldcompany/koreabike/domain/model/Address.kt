package com.goldcompany.koreabike.domain.model

data class Address(
    val id: String,
    val addressName: String,
    val roadAddressName: String,
    val categoryName: String,
    val distance: String,
    val phone: String,
    val placeName: String,
    val placeUrl: String,
    val x: String,
    val y: String
)
