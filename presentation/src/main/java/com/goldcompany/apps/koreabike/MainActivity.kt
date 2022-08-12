package com.goldcompany.apps.koreabike

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.navigation.fragment.NavHostFragment
import com.goldcompany.apps.koreabike.databinding.ActivityMainBinding
import com.goldcompany.apps.koreabike.ui.bike_map.BikeMapFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBottomNav()
    }

    private fun setInitialFragment() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragment_container_view, BikeMapFragment())
        }
    }

    private fun setBottomNav() {
//        bottomNav = binding.bottomNavigationView
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHostFragment.navController
//        bottomNav.setupWithNavController(navController)
    }
}