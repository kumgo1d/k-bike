package com.goldcompany.apps.koreabike

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.admanager.AdManagerAdRequest
import com.google.android.material.bottomnavigation.BottomNavigationView

interface navigationHandler {
    fun popBack()
}

class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var instance: MainActivity
            private set

        fun hideKeyboard(view: EditText) {
            view.clearFocus()

            val imm = instance.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        instance = this

        MobileAds.initialize(this)

        setBanner()
        setBottomNav()
    }

    private fun setBanner() {
        val adRequest = AdManagerAdRequest.Builder().build()
        findViewById<AdView>(R.id.publisherAdView).loadAd(adRequest)
    }

    private fun setBottomNav() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHostFragment.navController
        findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
            .setupWithNavController(navController)
    }
}