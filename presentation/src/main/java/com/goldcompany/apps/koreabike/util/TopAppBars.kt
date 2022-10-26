package com.goldcompany.apps.koreabike.util

import androidx.annotation.StringRes
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

enum class SearchAppBarState {
    OPENED,
    CLOSED
}

@Composable
fun ListPageTopAppBar(
    @StringRes title: Int,
    navigateBack: () -> Unit
) {
    TopAppBar(
        title = { 
            Text(
                text = stringResource(id = title),
                color = colorResource(id = R.color.white)
            )
        },
        navigationIcon = {
            IconButton(onClick = navigateBack) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.back_to_page),
                    tint = colorResource(id = R.color.white)
                )
            }
        },
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = colorResource(id = R.color.colorPrimary)
    )
}

@Preview
@Composable
private fun ListPageTopAppBarPreview() {
    MaterialTheme {
        Surface {
            ListPageTopAppBar(R.string.app_name, { })
        }
    }
}