package com.goldcompany.apps.koreabike.ui.navigation

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.goldcompany.apps.koreabike.NavGraphDirections
import com.goldcompany.apps.koreabike.R

public class NavigationFragmentDirections private constructor() {
  public companion object {
    public fun actionNavigationFragmentToNavigationMapFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_navigationFragment_to_navigationMapFragment)

    public fun actionGlobalMapView(): NavDirections = NavGraphDirections.actionGlobalMapView()
  }
}
