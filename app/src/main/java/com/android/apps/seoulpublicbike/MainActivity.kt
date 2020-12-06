package com.android.apps.seoulpublicbike

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setFragment()
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
}