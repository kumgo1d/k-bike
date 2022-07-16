package com.goldcompany.apps.koreabike.data.driving

import com.goldcompany.koreabike.data.model.driving.Guide
import com.goldcompany.koreabike.data.model.driving.Section
import com.goldcompany.koreabike.data.model.driving.Summary

data class Track(
    val guide: List<Guide>,
    val path: List<List<Double>>,
    val section: List<Section>,
    val summary: Summary
)