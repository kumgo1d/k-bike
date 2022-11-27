package com.goldcompany.apps.koreabike

import androidx.navigation.NavHostController

object KBikeDestinations {
    const val BIKE_MAP_SCREEN = "BikeMap"
    const val SEARCH_PLACE_SCREEN = "SearchPlace"
    const val MY_PLACE_SCREEN = "MyPlace"
    const val NAVIGATION_SCREEN = "Navigation"
}

class KBikeNavigationActions(private val navController: NavHostController) {
    fun popBackStack() {
        navController.popBackStack()
    }
}