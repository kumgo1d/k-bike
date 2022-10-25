package com.goldcompany.apps.koreabike

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.goldcompany.apps.koreabike.ui.bike_map.BikeMapScreen
import com.goldcompany.apps.koreabike.ui.history_place.HistoryPlaceScreen
import com.goldcompany.apps.koreabike.ui.search_address.SearchAddressScreen
import kotlinx.coroutines.CoroutineScope

@Composable
fun KBikeNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    startDestination: String = KBikeDestinations.BIKE_MAP_SCREEN
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(KBikeDestinations.BIKE_MAP_SCREEN) {
            BikeMapScreen(
                navigateSearchAddress = {
                    navController.navigate(KBikeDestinations.HISTORY_ADDRESS_SCREEN)
                }
            )
        }
        composable(KBikeDestinations.SEARCH_ADDRESS_SCREEN) {
            SearchAddressScreen(
                navigateBack = { KBikeNavigationActions(navController).popBackStack() }
            )
        }
        composable(KBikeDestinations.HISTORY_ADDRESS_SCREEN) {
            HistoryPlaceScreen(
                navigateBack = { KBikeNavigationActions(navController).popBackStack() }
            )
        }
    }
}