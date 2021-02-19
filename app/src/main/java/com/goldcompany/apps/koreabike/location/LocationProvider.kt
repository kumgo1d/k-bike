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
        //위치 서비스 활성화시
        //저장된 주소가 없다면 Fused Location으로 설정
        //저장된 주소가 있다면 가장 마지막으로 지정한 주소를 현재 주소로 설정
        //주소를 저장하는 데이터베이스 추가
        //저장된 주소는 즐겨찾기에서 확인 가능
        //즐겨찾기 목록 편집 가능
        
    }
}