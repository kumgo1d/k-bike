package com.android.apps.seoulpublicbike

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.android.apps.seoulpublicbike.favoritelist.FavoriteListFragment
import com.android.apps.seoulpublicbike.seoul.SeoulBikeMapFragment
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
        findNavController(R.id.nav_host_fragment).navigate(R.id.showBikeDataBottomSheet)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.apply {
            add(R.id.nav_host_fragment, seoulBikeMapFragment, SEOUL)
            add(R.id.nav_host_fragment, favoriteListFragment, FAVORITE).hide(favoriteListFragment)
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