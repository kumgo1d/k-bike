package com.goldcompany.apps.koreabike.ui.bike_map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.goldcompany.apps.koreabike.R
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BikeMapScreen(
    modifier: Modifier = Modifier,
    viewModel: BikeMapViewModel = viewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        val currentAddress = viewModel.currentAddress.observeAsState()
        val latitude = currentAddress.value?.y?.toDouble() ?: 37.5643
        val longitude = currentAddress.value?.x?.toDouble() ?: 126.9801
        val initialPosition = LatLng(latitude, longitude)

        BikeMap(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            initialPosition = initialPosition
        )
    }
}

@Composable
private fun BikeMap(
    modifier: Modifier,
    initialPosition: LatLng
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(initialPosition, 16f)
    }

    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = initialPosition),
            title = stringResource(id = R.string.current_location)
        )
    }
}

@Preview
@Composable
private fun BikeMapPreView() {
    MaterialTheme {
        Surface {
            BikeMap(
                modifier = Modifier.fillMaxSize(),
                initialPosition = LatLng(37.541, 126.986)
            )
        }
    }
}