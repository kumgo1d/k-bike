package com.android.apps.seoulpublicbike

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.android.apps.seoulpublicbike.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val HOME = "home"

    private val homeFragment = HomeFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragments()
    }

    override fun onStop() {
        super.onStop()
        supportFragmentManager.beginTransaction().detach(supportFragmentManager.findFragmentByTag(HOME)!!)
    }

    private fun addFragments() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.apply {
            add(R.id.fragment_container_view, homeFragment, HOME)
        }.commit()
    }
}