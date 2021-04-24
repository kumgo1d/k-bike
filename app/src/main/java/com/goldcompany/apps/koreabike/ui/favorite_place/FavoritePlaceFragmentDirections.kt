package com.goldcompany.apps.koreabike.ui.favorite_place

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.goldcompany.apps.koreabike.R

public class FavoritePlaceFragmentDirections private constructor() {
  public companion object {
    public fun actionFavoritePlaceFragmentToHomeFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_favorite_place_fragment_to_home_fragment)
  }
}
