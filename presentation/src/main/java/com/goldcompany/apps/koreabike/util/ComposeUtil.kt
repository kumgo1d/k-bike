package com.goldcompany.apps.koreabike.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goldcompany.apps.koreabike.R
import com.goldcompany.koreabike.domain.model.address.Address

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

@Composable
fun DefaultAddressItem(
    address: Address,
    onClick: (Address) -> Unit,
    navigateBack: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.list_item_horizontal_margin))
            .clickable {
                onClick(address)
                navigateBack()
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_search_button),
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
        ) {
            AddressText(text = address.placeName)
            AddressText(text = address.addressName)
        }
    }
}

@Preview
@Composable
private fun DefaultAddressItemPreView() {
    MaterialTheme {
        Surface {
            DefaultAddressItem(
                address = Address("", "addressNameaddressNameaddressNameaddressName", "", "", "", "placeName", "", "", ""),
                onClick = {},
                navigateBack = {}
            )
        }
    }
}