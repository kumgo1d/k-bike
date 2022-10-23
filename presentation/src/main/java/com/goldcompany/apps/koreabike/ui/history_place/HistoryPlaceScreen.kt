package com.goldcompany.apps.koreabike.ui.history_place

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.util.ListPageTopAppBars
import com.goldcompany.koreabike.domain.model.address.Address

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HistoryPlaceScreen(
    modifier: Modifier = Modifier,
    viewModel: HistoryPlaceViewModel = viewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { 
            ListPageTopAppBars(
                title = R.string.search_list,
                navigateBack = { }
            )
        },
        modifier = modifier.fillMaxSize(),
    ) { paddingValues ->
        val uiState by viewModel.uiState.collectAsState()

        if (uiState.isLoading) {

        } else {
            AddressLazyColumn(
                modifier = Modifier.padding(paddingValues),
                addressList = uiState.items,
                deleteAddress = viewModel::deleteAddress,
                onClick = viewModel::setCurrentAddress
            )
        }
    }
}

@Composable
private fun AddressLazyColumn(
    modifier: Modifier,
    addressList: List<Address>,
    deleteAddress: (Address) -> Unit,
    onClick: (Address) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp),
        modifier = modifier
    ) {
        items(addressList) { address ->
            HistoryPlaceItemView(
                address = address,
                deleteAddress = { deleteAddress(address) },
                onClick = { onClick(address) }
            )
        }
    }
}

@Composable
private fun HistoryPlaceItemView(
    address: Address,
    deleteAddress: (Address) -> Unit,
    onClick: (Address) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .clickable { onClick(address) }
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_search_button),
            contentDescription = null,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Column {
            Text(text = address.placeName)
            Text(text = address.addressName)
        }
        Image(
            painter = painterResource(id = R.drawable.ic_delete_button),
            contentDescription = null,
            modifier = Modifier
                .wrapContentWidth(Alignment.End)
                .clickable{ deleteAddress(address) },
            alignment = Alignment.TopEnd
        )
    }
}