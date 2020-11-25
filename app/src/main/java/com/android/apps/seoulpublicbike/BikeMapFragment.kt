package com.android.apps.seoulpublicbike

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.UiThread
import com.android.apps.seoulpublicbike.data.Bike
import com.naver.maps.geometry.LatLng
import com.naver.maps.geometry.LatLngBounds
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BikeMapFragment : Fragment(), OnMapReadyCallback {
    private lateinit var naverMap: NaverMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bike_map, container, false)
    }

    @UiThread
    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap
        naverMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_BUILDING, true)
        Log.d("Response", "로드")
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
                Toast.makeText(context, "서버에 오류가 발생하였습니다.", Toast.LENGTH_LONG).show()
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