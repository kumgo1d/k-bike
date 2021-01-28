package com.goldcompany.apps.koreabike

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.goldcompany.apps.koreabike.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigationFragments()
    }

    private fun navigationFragments() {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.map_view -> {
                    findNavController(R.id.fragment_container_view).navigate(R.id.seoulBikeMapFragment)
                }
                R.id.favorite_list -> {
                    findNavController(R.id.fragment_container_view).navigate(R.id.favoriteListFragment)
                }
            }
            true
        }
    }
}