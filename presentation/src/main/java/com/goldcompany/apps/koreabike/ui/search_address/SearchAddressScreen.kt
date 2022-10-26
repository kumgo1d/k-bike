package com.goldcompany.apps.koreabike.ui.search_address

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.util.DefaultAddressItem
import com.goldcompany.koreabike.domain.model.address.Address
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun SearchAddressScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchAddressViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navigateBack: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        topBar = {
            SearchAppBars(
                title = R.string.search_list,
                navigateBack = navigateBack
            )
        }
    ) { paddingValues ->
        AddressLazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            addressList = emptyList(),
            onClick = {  }
        )
    }
}

@Composable
private fun SearchAppBars(
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
        backgroundColor = colorResource(id = R.color.colorPrimary),
        actions = {
            IconButton(
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = colorResource(id = R.color.white)
                )
            }
        }
    )
}

@Composable
private fun AddressLazyColumn(
    modifier: Modifier,
    addressList: List<Address>,
    onClick: (Address) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp),
        modifier = modifier
    ) {
        items(addressList) { address ->
            DefaultAddressItem(
                address = address,
                onClick = onClick
            )
        }
    }
}

@Preview
@Composable
private fun SearchAppBarsPreView() {
    MaterialTheme {
        Surface {
            SearchAppBars(
                title = R.string.app_name,
                navigateBack = { }
            )
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
                onClick = { }
            )
        }
    }
}