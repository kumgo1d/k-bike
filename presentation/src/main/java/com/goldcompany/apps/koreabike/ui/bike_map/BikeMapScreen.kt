package com.goldcompany.apps.koreabike.ui.bike_map

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.goldcompany.apps.koreabike.KBikeDestinations
import com.goldcompany.apps.koreabike.R
import com.goldcompany.koreabike.domain.model.address.Address
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState

@Composable
fun BikeMapScreen(
    modifier: Modifier = Modifier,
    viewModel: BikeMapViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavController
) {
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = modifier.fillMaxSize(),
    ) { paddingValues ->
        val uiState by viewModel.uiState.collectAsState()

        BikeMap(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            address = uiState.address
        )

        SearchAddressBar { navController.navigate(KBikeDestinations.SEARCH_PLACE_SCREEN) }
    }
}

@Composable
private fun BikeMap(
    modifier: Modifier,
    address: Address?
) {
    val latitude = address?.y?.toDouble() ?: 37.5643
    val longitude = address?.x?.toDouble() ?: 126.9801
    val initialPosition = LatLng(latitude, longitude)

    GoogleMap(
        modifier = modifier,
        cameraPositionState = CameraPositionState(
            position = CameraPosition.fromLatLngZoom(initialPosition, 16f)
        )
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
            .padding(8.dp)
            .fillMaxWidth(),
        shape = Shapes(medium = RoundedCornerShape(16.dp)).medium,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = R.color.white),
            contentColor = colorResource(id = R.color.colorPrimary)
        ),
        border = BorderStroke(1.dp, colorResource(id = R.color.colorPrimary)),
        onClick = navigateSearchAddress,
        content = {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.search_address_hint2),
                textAlign = TextAlign.Start
            )
        }
    )
}

@Preview
@Composable
private fun BikeMapPreView() {
    MaterialTheme {
        Surface {
            BikeMap(
                modifier = Modifier.fillMaxSize(),
                address = null
            )
            SearchAddressBar({ })
        }
    }
}