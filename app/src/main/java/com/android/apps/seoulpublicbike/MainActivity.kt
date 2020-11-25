package com.android.apps.seoulpublicbike

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.UiThread
import com.android.apps.seoulpublicbike.data.Bike
import com.naver.maps.geometry.LatLng
import com.naver.maps.geometry.LatLngBounds
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var naverMap: NaverMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setFragment()

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as MapFragment?
            ?: MapFragment.newInstance().also {
                supportFragmentManager.beginTransaction().add(R.id.map, it).commit()
            }
        mapFragment.getMapAsync(this)
    }

    fun setFragment() {
        val bikeMapFragment: BikeMapFragment = BikeMapFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.frameLayout, bikeMapFragment)
        transaction.commit()
    }

    @UiThread
    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap
        naverMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_BUILDING, true)
        loadBikeList()
    }

    private fun loadBikeList() {
        val retrofit = Retrofit.Builder()
            .baseUrl(SeoulOpenApi.DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val seoulOpenService = retrofit.create(SeoulOpenService::class.java)
        seoulOpenService.getBike(SeoulOpenApi.API_KEY).enqueue(object : Callback<Bike> {
            override fun onResponse(call: Call<Bike>, response: Response<Bike>) {
                showBikeList(response.body() as Bike)
            }

            override fun onFailure(call: Call<Bike>, t: Throwable) {
                Toast.makeText(baseContext, "서버에 오류가 발생하였습니다.", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun showBikeList(bike: Bike) {
        val latLngBounds = LatLngBounds.Builder()

        for(b in bike.rentBikeStatus.row) {
            val pos = LatLng(b.stationLatitude.toDouble(), b.stationLongitude.toDouble())
            val marker = Marker()
            marker.apply {
                position = pos
                captionText = b.stationName
                map = naverMap
            }

            latLngBounds.include(marker.position)
        }
    }
}