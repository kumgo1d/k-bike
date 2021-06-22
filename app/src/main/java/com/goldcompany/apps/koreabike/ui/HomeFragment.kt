package com.goldcompany.apps.koreabike.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.databinding.FragmentHomeBinding
import com.goldcompany.apps.koreabike.ui.favorite_bike.FavoriteBikeListFragment
import com.goldcompany.apps.koreabike.ui.bike_map.BikeMapFragment
import com.goldcompany.apps.koreabike.ui.webview.WebViewFragment
import com.google.android.gms.ads.admanager.AdManagerAdRequest

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        childFragmentManager.beginTransaction().replace(R.id.fragment_container_view, BikeMapFragment(), "map").commit()

        setBanner()

        navigationFragments()

        return binding.root
    }

    private fun setBanner() {
        val adRequest = AdManagerAdRequest.Builder().build()
        binding.publisherAdView.loadAd(adRequest)
    }

    private fun navigationFragments() {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.map_view -> {
                    childFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view, BikeMapFragment(), "map")
                        .commit()
                }
                R.id.favorite_list -> {
                    childFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view, FavoriteBikeListFragment(), "list")
                        .commit()
                }
                R.id.webview -> {
                    childFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view, WebViewFragment(), "web")
                        .commit()
                }
            }
            true
        }
    }
}