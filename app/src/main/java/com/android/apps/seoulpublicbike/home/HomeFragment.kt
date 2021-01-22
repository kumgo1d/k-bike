package com.android.apps.seoulpublicbike.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.android.apps.seoulpublicbike.R
import com.android.apps.seoulpublicbike.databinding.FragmentHomeBinding
import com.android.apps.seoulpublicbike.favoritelist.FavoriteListFragment
import com.android.apps.seoulpublicbike.seoul.SeoulBikeMapFragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val seoulBikeMapFragment = SeoulBikeMapFragment()
    private val favoriteListFragment = FavoriteListFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigationFragments()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigationFragments() {

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.map_view -> {
                    findNavController().navigate(R.id.action_homeFragment_to_seoulBikeMapFragment)
                }
                R.id.favorite_list -> {
                    findNavController().navigate(R.id.action_homeFragment_to_favoriteListFragment)
                }
            }
            true
        }
    }
}