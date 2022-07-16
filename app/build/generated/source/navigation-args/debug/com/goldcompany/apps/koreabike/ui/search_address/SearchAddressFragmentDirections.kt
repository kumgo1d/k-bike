package com.goldcompany.apps.koreabike.ui.search_address

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.goldcompany.apps.koreabike.NavGraphDirections
import com.goldcompany.apps.koreabike.R

public class SearchAddressFragmentDirections private constructor() {
  public companion object {
    public fun actionSearchAddressFragmentToFavoritePlaceFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_search_address_fragment_to_favorite_place_fragment)

    public fun actionGlobalMapView(): NavDirections = NavGraphDirections.actionGlobalMapView()
  }
}
