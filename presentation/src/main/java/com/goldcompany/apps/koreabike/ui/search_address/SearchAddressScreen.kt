package com.goldcompany.apps.koreabike.ui.search_address

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.util.DefaultAddressItem
import com.goldcompany.apps.koreabike.util.SearchAppBarState
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
                onSearch = { viewModel.searchAddress() }
            )
        }
    ) { paddingValues ->
        val uiState by viewModel.uiState.collectAsState()

        if (uiState.isLoading) {
            Box(modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    color = colorResource(id = R.color.colorPrimary),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        } else {
            AddressLazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                addressList = uiState.items,
                onClick = {  }
            )
        }
    }
}

@Composable
private fun SearchAppBar(
    @StringRes title: Int,
    place: String,
    searchAppBarState: SearchAppBarState,
    navigateBack: () -> Unit,
    onClickForSearch: () -> Unit,
    onClickForSearchClose: () -> Unit,
    onSearchPlaceChange: (String) -> Unit,
    onSearch: () -> Unit
) {
    when (searchAppBarState) {
        SearchAppBarState.OPENED -> {
            SearchTextField(
                place = place,
                onClickForSearchClose = onClickForSearchClose,
                onSearchPlaceChange = onSearchPlaceChange,
                onSearch = onSearch
            )
        }
        SearchAppBarState.CLOSED -> {
            DefaultSearchAppBar(
                title = title,
                onClickForSearch = onClickForSearch,
                navigateBack = navigateBack
            )
        }
    }
}

@Composable
private fun DefaultSearchAppBar(
    @StringRes title: Int,
    onClickForSearch: () -> Unit,
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
                onClick = onClickForSearch
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
private fun SearchTextField(
    place: String,
    onSearchPlaceChange: (String) -> Unit,
    onClickForSearchClose: () -> Unit,
    onSearch: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = colorResource(id = R.color.colorPrimary)
    ) {
        TextField(
            modifier = Modifier.padding(horizontal = 4.dp),
            value = place,
            onValueChange = {
                onSearchPlaceChange(it)
                onSearch()
            },
            textStyle = TextStyle(
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight(700)
            ),
            colors = TextFieldDefaults.textFieldColors(
                textColor = colorResource(id = R.color.white),
                backgroundColor = Color.Transparent,
                cursorColor = colorResource(id = R.color.white),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                placeholderColor = colorResource(id = R.color.white)
            ),
            leadingIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                        tint = colorResource(id = R.color.white)
                    )
                }
            },
            trailingIcon = {
                IconButton(onClick = onClickForSearchClose) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = null,
                        tint = colorResource(id = R.color.white)
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch()
                }
            ),
            maxLines = 1
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
private fun DefaultSearchAppBarPreView() {
    MaterialTheme {
        Surface {
            DefaultSearchAppBar(
                title = R.string.app_name,
                onClickForSearch = {},
                navigateBack = {}
            )
        }
    }
}

@Preview
@Composable
private fun SearchTextFieldPreView() {
    MaterialTheme {
        SearchTextField(place = "Some Place", {}, {}, {})
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