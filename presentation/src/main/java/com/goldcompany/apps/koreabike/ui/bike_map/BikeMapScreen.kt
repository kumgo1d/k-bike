package com.goldcompany.apps.koreabike.ui.bike_map

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.goldcompany.apps.koreabike.KBikeScreen
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.util.KBikeTypography
import com.goldcompany.koreabike.domain.model.address.Address
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun BikeMapScreen(
    modifier: Modifier = Modifier,
    viewModel: BikeMapViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavController
) {
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = modifier.fillMaxSize(),
    ) { paddingValues ->

        val uiState by viewModel.uiState.collectAsState()
        val bottomSheetUiState by viewModel.bottomSheetUiState.collectAsState()

        BikeMapDefaultScreen(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            address = uiState.address,
            bottomSheetUiState = bottomSheetUiState,
            coroutineScope = coroutineScope,
            searchPlace = viewModel::searchPlace,
            navigateSearchAddress = {
                navController.navigate(KBikeScreen.SearchPlace.route)
            }
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun BikeMapDefaultScreen(
    modifier: Modifier,
    address: Address?,
    bottomSheetUiState: BikeMapBottomSheetUiState,
    coroutineScope: CoroutineScope,
    searchPlace: (String) -> Unit,
    navigateSearchAddress: () -> Unit
) {
    val bottomState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )

    BikeMap(
        modifier = modifier,
        address = address,
        searchPlace = searchPlace,
        coroutineScope = coroutineScope,
        bottomState = bottomState
    )
    SearchAddressBar(navigateSearchAddress = navigateSearchAddress)
    BottomSheetLayout(
        bottomSheetUiState = bottomSheetUiState,
        bottomState = bottomState,
        coroutineScope = coroutineScope
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun BikeMap(
    modifier: Modifier,
    address: Address?,
    searchPlace: (String) -> Unit,
    coroutineScope: CoroutineScope,
    bottomState: ModalBottomSheetState
) {
    val latitude = address?.y?.toDouble() ?: 37.5643
    val longitude = address?.x?.toDouble() ?: 126.9801
    val initialPosition = LatLng(latitude, longitude)

    GoogleMap(
        modifier = modifier,
        cameraPositionState = CameraPositionState(
            position = CameraPosition.fromLatLngZoom(initialPosition, 16f)
        ),
        onPOIClick = { marker ->
            searchPlace(marker.name)
            coroutineScope.launch {
                bottomState.show()
            }
        }
    ) {
        Marker(
            state = MarkerState(position = initialPosition),
            title = stringResource(id = R.string.current_location)
        )
    }
}

@Composable
private fun SearchAddressBar(
    navigateSearchAddress: () -> Unit
) {
    Button(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth(),
        shape = Shapes(medium = RoundedCornerShape(6.dp)).medium,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = R.color.white),
            contentColor = colorResource(id = R.color.black)
        ),
        border = BorderStroke(1.dp, colorResource(id = R.color.black)),
        onClick = navigateSearchAddress,
        content = {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.search_address_hint2),
                textAlign = TextAlign.Start,
                style = KBikeTypography.button
            )
        }
    )
}

@SuppressLint("SetJavaScriptEnabled")
@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun BottomSheetLayout(
    bottomSheetUiState: BikeMapBottomSheetUiState,
    bottomState: ModalBottomSheetState,
    coroutineScope: CoroutineScope,
) {
    var backEnabled by remember { mutableStateOf(false) }
    val webView: WebView? = null

    BackHandler(enabled = backEnabled) {
        webView?.goBack()
    }

    ModalBottomSheetLayout(
        sheetState = bottomState,
        sheetContent = {
            Text(
                modifier = Modifier.padding(8.dp),
                text = bottomSheetUiState.currentPlace?.placeName.toString(),
                style = KBikeTypography.h1
            )
            Text(
                modifier = Modifier.padding(8.dp),
                text = bottomSheetUiState.currentPlace?.roadAddressName.toString(),
                style = KBikeTypography.button
            )
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { context ->
                    WebView(context).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        webViewClient = object : WebViewClient() {
                            override fun onPageStarted(view: WebView, url: String?, favicon: Bitmap?) {
                                backEnabled = view.canGoBack()
                            }
                        }
                        settings.javaScriptEnabled = true
                    }
                },
                update = { webView ->
                    webView.loadUrl(bottomSheetUiState.currentPlace?.placeUrl ?: "")
                }
            )
        }
    ) {
        // Anchor
    }
}

@Preview
@Composable
private fun SearchAddressBarPreView() {
    MaterialTheme {
        Surface {
            SearchAddressBar({})
        }
    }
}