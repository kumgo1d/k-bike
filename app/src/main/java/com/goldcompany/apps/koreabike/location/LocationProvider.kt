package com.goldcompany.apps.koreabike.location

import android.annotation.SuppressLint
import android.location.Location
import com.goldcompany.apps.koreabike.MainActivity
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

object LocationProvider {
    private lateinit var fusedLocation: FusedLocationProviderClient

    private val _location = MutableStateFlow<Location?>(null)

    @SuppressLint("MissingPermission")
    private fun startUpdateLocation() {
        fusedLocation = FusedLocationProviderClient(MainActivity.instance)

        fusedLocation.lastLocation.addOnSuccessListener {
            if(it != null) {
                GlobalScope.launch {
                    _location.emit(it)
                }
            } else {
                requestLocationUpdate()
            }
        }
    }

    private fun requestLocationUpdate() {

    }
}