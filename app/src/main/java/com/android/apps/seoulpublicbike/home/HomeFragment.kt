package com.android.apps.seoulpublicbike.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.apps.seoulpublicbike.R
import com.android.apps.seoulpublicbike.databinding.FragmentBikeMapBinding
import com.android.apps.seoulpublicbike.databinding.FragmentHomeBinding
import com.android.apps.seoulpublicbike.seoul.SeoulBikeMapFragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val seoulBikeMapFragment = SeoulBikeMapFragment()

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

        addFragments()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addFragments() {
        val transaction = childFragmentManager.beginTransaction()
        transaction.apply {
            add(R.id.fragment_container_view, seoulBikeMapFragment, "seoul")
        }.commit()
    }
//
//    private fun navigationFragments() {
//        var activeFragment: Fragment = seoulBikeMapFragment
//        bottom_navigation_view.setOnNavigationItemSelectedListener {
//            when(it.itemId) {
//                R.id.map_view -> {
//                    supportFragmentManager.beginTransaction().detach(activeFragment).show(seoulBikeMapFragment).commit()
//                    activeFragment = seoulBikeMapFragment
//                }
//                R.id.favorite_list -> {
//                    supportFragmentManager.beginTransaction().hide(activeFragment).attach(favoriteListFragment).show(favoriteListFragment).commit()
//                    activeFragment = favoriteListFragment
//                }
//            }
//            true
//        }
//    }
}