package com.goldcompany.apps.koreabike.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.goldcompany.apps.koreabike.R
import com.goldcompany.koreabike.domain.model.Address

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HistoryPlaceItemView(
    address: Address,
    deleteAddress: () -> Unit,
    onClick: () -> Unit
) {
    Surface(onClick = onClick) {
        Row {
            val searchIcon = painterResource(id = R.drawable.ic_search_button)
            Image(
                painter = searchIcon,
                contentDescription = null,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Column {
                Text(
                    text = address.placeName
                )
                Text(
                    text = address.addressName
                )
            }
            Surface(onClick = deleteAddress) {
                val deleteIcon = painterResource(id = R.drawable.ic_delete_button)
                Image(
                    painter = deleteIcon,
                    contentDescription = null,
                    modifier = Modifier.padding(horizontal = 8.dp),
                )
            }
        }
    }
}