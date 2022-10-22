package com.goldcompany.apps.koreabike.util

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.goldcompany.apps.koreabike.R

@Composable
fun ListPageTopAppBars(
    title: Int,
    navigateBack: () -> Unit
) {
    TopAppBar(
        title = { 
            Text(
                text = stringResource(id = title),
                color = colorResource(id = R.color.black)
            )
        },
        navigationIcon = {
            IconButton(onClick = navigateBack) {
                Icon(Icons.Filled.ArrowBack, stringResource(id = R.string.back_to_page))
            }
        },
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = colorResource(id = R.color.white)
    )
}

@Preview
@Composable
private fun AddressListTopAppBarsPreview() {
    MaterialTheme {
        Surface {
            ListPageTopAppBars(R.string.app_name, { })
        }
    }
}