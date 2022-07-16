package com.goldcompany.apps.koreabike.ui.bike_map

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.goldcompany.apps.koreabike.NavGraphDirections
import com.goldcompany.apps.koreabike.R

public class BikeMapFragmentDirections private constructor() {
  public companion object {
    public fun actionMapViewToSearchAddressFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_map_view_to_search_address_fragment)

    public fun actionMapViewToNavigationFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_map_view_to_navigationFragment)

    public fun actionGlobalMapView(): NavDirections = NavGraphDirections.actionGlobalMapView()
  }
}
