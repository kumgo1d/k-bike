package com.goldcompany.apps.koreabike

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections

public class NavGraphDirections private constructor() {
  public companion object {
    public fun actionGlobalMapView(): NavDirections =
        ActionOnlyNavDirections(R.id.action_global_map_view)
  }
}
