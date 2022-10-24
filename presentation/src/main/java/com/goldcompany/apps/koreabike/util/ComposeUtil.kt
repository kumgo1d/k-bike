package com.goldcompany.apps.koreabike.util

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun AddressText(
    text: String
) {
    Text(
        text = text,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}