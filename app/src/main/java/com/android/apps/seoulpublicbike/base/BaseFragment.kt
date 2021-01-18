package com.android.apps.seoulpublicbike.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.apps.seoulpublicbike.R
import com.android.apps.seoulpublicbike.databinding.FragmentBaseBinding
import com.android.apps.seoulpublicbike.favoritelist.FavoriteListFragment
import com.android.apps.seoulpublicbike.seoul.SeoulBikeMapFragment

class BaseFragment : Fragment() {

    private var _binding: FragmentBaseBinding? = null
    private val binding get() = _binding!!

    private val seoulBikeMapFragment = SeoulBikeMapFragment()
    private val favoriteListFragment = FavoriteListFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBaseBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addFragments()
        navigationFragments()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addFragments() {
        val transaction = childFragmentManager.beginTransaction()
        transaction.apply {
            add(R.id.fragment_container_view, seoulBikeMapFragment, "seoul")
            add(R.id.fragment_container_view, favoriteListFragment, "favorite").hide(favoriteListFragment)
        }.commit()
    }

    private fun navigationFragments() {
        var activeFragment: Fragment = seoulBikeMapFragment
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.map_view -> {
                    childFragmentManager.beginTransaction().detach(activeFragment).show(seoulBikeMapFragment).commit()
                    activeFragment = seoulBikeMapFragment
                }
                R.id.favorite_list -> {
                    childFragmentManager.beginTransaction().hide(activeFragment).attach(favoriteListFragment).show(favoriteListFragment).commit()
                    activeFragment = favoriteListFragment
                }
            }
            true
        }
    }
}