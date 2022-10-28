package com.goldcompany.apps.koreabike.ui.search_address

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.util.DefaultAddressItem
import com.goldcompany.apps.koreabike.util.LoadingState
import com.goldcompany.apps.koreabike.util.SearchAppBar
import com.goldcompany.koreabike.domain.model.address.Address

@Composable
fun SearchAddressScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchAddressViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navigateBack: () -> Unit
) {
    val searchAppBarState by viewModel.searchAppBarState
    val searchAddressState by viewModel.searchAddressState

    Scaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        topBar = {
            SearchAppBar(
                title = R.string.search_list,
                place = searchAddressState,
                searchAppBarState = searchAppBarState,
                navigateBack = navigateBack,
                onClickForSearch = { viewModel.setSearchAppBarStateOpen() },
                onClickForSearchClose = { viewModel.setSearchAppBarStateClose() },
                onSearchPlaceChange = viewModel::setSearchAddressState,
                onSearch = viewModel::searchAddress
            )
        }
    ) { paddingValues ->
        val uiState by viewModel.uiState.collectAsState()
        val commonModifier = Modifier.fillMaxSize().padding(8.dp)

        when (uiState.isLoading) {
            LoadingState.INIT -> {
                Text(
                    text = stringResource(id = R.string.init_page),
                    modifier = commonModifier,
                    textAlign = TextAlign.Center
                )
            }
            LoadingState.LOADING -> {
                Box(modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        color = colorResource(id = R.color.colorPrimary),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            LoadingState.DONE -> {
                SearchAddressColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    addressList = uiState.items,
                    onClick = {  }
                )
            }
            else -> {
                Text(
                    text = stringResource(id = R.string.error_code),
                    modifier = commonModifier,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun SearchAddressColumn(
    modifier: Modifier,
    addressList: List<Address>,
    onClick: (Address) -> Unit
) {
    if (addressList.isEmpty()) {
        Text(
            text = stringResource(id = R.string.no_data),
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            textAlign = TextAlign.Center
        )
    } else {
        AddressLazyColumn(
            modifier = modifier,
            addressList = addressList,
            onClick = onClick
        )
    }
}

@Composable
private fun AddressLazyColumn(
    modifier: Modifier,
    addressList: List<Address>,
    onClick: (Address) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
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