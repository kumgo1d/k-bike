package com.goldcompany.apps.koreabike.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.databinding.FragmentHomeBinding
import com.goldcompany.apps.koreabike.favorite_list.FavoriteListFragment
import com.goldcompany.apps.koreabike.seoul.SeoulBikeMapFragment

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        childFragmentManager.beginTransaction().replace(R.id.fragment_container_view, SeoulBikeMapFragment(), "map").commit()

        navigationFragments()

        return binding.root
    }

    private fun navigationFragments() {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.map_view -> {
                    childFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view, SeoulBikeMapFragment(), "map")
                        .commit()
                }
                R.id.favorite_list -> {
                    childFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view, FavoriteListFragment(), "list")
                        .commit()
                }
            }
            true
        }
    }
}