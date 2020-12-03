package com.android.apps.seoulpublicbike

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setFragment()
    }

//    override fun onRestart() {
//        super.onRestart()
//        val seoulBikeMapFragment = SeoulBikeMapFragment()
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.detach(seoulBikeMapFragment)
//        transaction.attach(seoulBikeMapFragment)
//    }

    private fun setFragment() {
        val seoulBikeMapFragment = SeoulBikeMapFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.frameLayout, seoulBikeMapFragment)
        transaction.commit()
    }
}