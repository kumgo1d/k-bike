package com.goldcompany.apps.koreabike

import androidx.navigation.NavHostController

object KBikeDestinations {
    const val BIKE_MAP_SCREEN = "home"
    const val SEARCH_ADDRESS_SCREEN = "searchAddress"
    const val HISTORY_ADDRESS_SCREEN = "historyAddress"
    const val NAVIGATION_SCREEN = "navigation"
}

class KBikeNavigationActions(private val navController: NavHostController) {

}