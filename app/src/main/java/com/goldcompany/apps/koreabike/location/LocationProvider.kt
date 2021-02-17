package com.goldcompany.apps.koreabike.location

import android.annotation.SuppressLint
import android.location.Location
import com.goldcompany.apps.koreabike.MainActivity
import com.google.android.gms.location.FusedLocationProviderClient

object LocationProvider {
    private lateinit var fusedLocation: FusedLocationProviderClient

    private val _location = MutableStateFlow<Location?>(null)

    @SuppressLint("MissingPermission")
    private fun startUpdateLocation() {
        fusedLocation = FusedLocationProviderClient(MainActivity.instance)

        if(true) {
            fusedLocation.lastLocation.addOnSuccessListener {

            }
        }


    }
}