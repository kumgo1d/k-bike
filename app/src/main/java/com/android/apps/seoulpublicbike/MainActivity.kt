package com.android.apps.seoulpublicbike

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setFragment()

        bottom_navigation_view.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.map_view -> {
                    setFragment()
                }
                R.id.favorite_list -> {

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

    private fun setFragment() {
        val seoulBikeMapFragment = SeoulBikeMapFragment()
        val transaction = supportFragmentManager.beginTransaction()
        if(supportFragmentManager.findFragmentByTag("seoul") == null) {
            transaction.add(R.id.frameLayout, seoulBikeMapFragment, "seoul")
        } else {
            transaction.attach(supportFragmentManager.findFragmentByTag("seoul")!!)
        }
        transaction.commit()
    }

    private fun setLIstFragment() {
        val favoriteListFragment = FavoriteListFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, favoriteListFragment, "favorite")
        transaction.commit()
    }
}