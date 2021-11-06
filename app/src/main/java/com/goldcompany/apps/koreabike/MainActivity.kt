package com.goldcompany.apps.koreabike

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.goldcompany.apps.koreabike.databinding.ActivityMainBinding
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.admanager.AdManagerAdRequest
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var banner: AdView

    companion object {
        lateinit var instance: MainActivity
            private set
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        instance = this

        MobileAds.initialize(this)

        setAdBanner()
        setBottomNav()
    }

    private fun setAdBanner() {
        banner = binding.publisherAdView
        val adRequest = AdManagerAdRequest.Builder().build()
        banner.loadAd(adRequest)
    }

    private fun setBottomNav() {
        bottomNav = binding.bottomNavigationView
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNav.setupWithNavController(navController)
    }

    fun hideKeyboard(view: View) {
        val imm = instance.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun hideBottom() {
        banner.visibility = View.GONE
        bottomNav.visibility = View.GONE
    }

    fun showBottom() {
        banner.visibility = View.VISIBLE
        bottomNav.visibility = View.VISIBLE
    }
}