package com.android.apps.seoulpublicbike

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val SEOUL = "seoul"
    private val FAVORITE = "favorite"

    private val seoulBikeMapFragment = SeoulBikeMapFragment()
    private val favoriteListFragment = FavoriteListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragments()
        navigationFragments()
    }

    override fun onStop() {
        super.onStop()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.detach(supportFragmentManager.findFragmentByTag("seoul")!!)
    }

    private fun addFragments() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.apply {
            add(R.id.frameLayout, seoulBikeMapFragment, SEOUL)
            add(R.id.frameLayout, favoriteListFragment, FAVORITE).hide(favoriteListFragment)
        }.commit()
    }

    private fun navigationFragments() {
        var activeFragment: Fragment = seoulBikeMapFragment
        bottom_navigation_view.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.map_view -> {
                    supportFragmentManager.beginTransaction().detach(activeFragment).show(seoulBikeMapFragment).commit()
                    activeFragment = seoulBikeMapFragment
                }
                R.id.favorite_list -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).attach(favoriteListFragment).show(favoriteListFragment).commit()
                    activeFragment = favoriteListFragment
                }
            }
            true
        }
    }
}