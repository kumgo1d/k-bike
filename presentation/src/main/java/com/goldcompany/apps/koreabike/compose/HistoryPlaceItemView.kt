package com.goldcompany.apps.koreabike.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.goldcompany.apps.koreabike.R
import com.goldcompany.koreabike.domain.model.address.Address

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HistoryPlaceItemView(
    address: Address,
    deleteAddress: () -> Unit,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 8.dp),
        onClick = onClick
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.ic_search_button),
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
        }
        Image(
            painter = painterResource(id = R.drawable.ic_delete_button),
            contentDescription = null,
            modifier = Modifier
                .wrapContentWidth(Alignment.End)
                .clickable { deleteAddress },
            alignment = Alignment.TopEnd
        )
    }
}