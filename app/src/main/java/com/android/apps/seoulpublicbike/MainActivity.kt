package com.android.apps.seoulpublicbike

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        val seoulBikeMapFragment = SeoulBikeMapFragment()
        val favoriteListFragment = FavoriteListFragment()
    }

    private val SEOUL = "seoul"
    private val FAVORITE = "favorite"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setFragment(seoulBikeMapFragment, SEOUL)

        bottom_navigation_view.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.map_view -> {
                    val bundle = Bundle()
                    bundle.putBoolean("mapAttach", true)
                    seoulBikeMapFragment.arguments = bundle

                    setFragment(seoulBikeMapFragment, SEOUL)
                }
                R.id.favorite_list -> {
                    //setFragment(favoriteListFragment, FAVORITE)
                }
            }
            true
        }
    }

    override fun onStop() {
        super.onStop()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.detach(supportFragmentManager.findFragmentByTag("seoul")!!)
    }

    private fun setFragment(fragment: Fragment, tag: String) {
        val transaction = supportFragmentManager.beginTransaction()
//        if(supportFragmentManager.findFragmentByTag(tag) == null) {
//            transaction.add(R.id.frameLayout, fragment, tag)
//        } else {
//            transaction.attach(supportFragmentManager.findFragmentByTag(tag)!!)
//        }
        transaction.replace(R.id.frameLayout, fragment, tag)
        transaction.commit()
    }
}