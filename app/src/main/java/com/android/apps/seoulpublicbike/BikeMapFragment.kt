package com.android.apps.seoulpublicbike

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import com.android.apps.seoulpublicbike.data.Bike
import com.naver.maps.geometry.LatLng
import com.naver.maps.geometry.LatLngBounds
import com.naver.maps.map.*
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay
import com.naver.maps.map.util.FusedLocationSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BikeMapFragment : Fragment(), OnMapReadyCallback, LocationListener {
    private lateinit var locationSource: FusedLocationSource
    private lateinit var naverMap: NaverMap
    private var locationManager: LocationManager? = null

    //내 위치
    private var longitude: Double = 0.0
    private var latitude: Double = 0.0

    companion object {
        const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //map fragment와 sync
        val mapFragment = fragmentManager?.findFragmentById(R.id.map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fragmentManager?.beginTransaction()?.add(R.id.map, it)?.commit()
            }
        mapFragment.getMapAsync(this)

        //권한 설정 여부와 지도 위 위치 표시를 위한 location source
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
        //내 위치를 알기 위한 location manager
        locationManager = (activity as AppCompatActivity).getSystemService(LOCATION_SERVICE) as LocationManager?
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        //권한을 거부한 뒤에 다시 허용을 눌렀을 때, 정상 작동하기 위한 if문
        if(ContextCompat.checkSelfPermission(context!!, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10f, this)
            locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
            initMapSettings()
            loadBikeList()
        }
        if(ContextCompat.checkSelfPermission(context!!, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            loadBikeList()
        }
    }

    override fun onStart() {
        super.onStart()
        //권한
        checkPermissions()
    }

    override fun onStop() {
        super.onStop()
        locationManager?.removeUpdates(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bike_map, container, false)
    }

    @UiThread
    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap
        val uiSettings = naverMap.uiSettings
        uiSettings.isLogoClickEnabled = false
        //naverMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_BICYCLE, true)

        if(ContextCompat.checkSelfPermission(context!!, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            var cameraPosition = CameraPosition(LatLng(latitude, longitude), 16.0)
            naverMap.cameraPosition = cameraPosition
            initMapSettings()
        } else {
            var cameraPosition = CameraPosition(LatLng(37.5643, 126.9801), 15.0)
            naverMap.cameraPosition = cameraPosition
            naverMap.locationTrackingMode = LocationTrackingMode.None
        }
        loadBikeList()
    }

    private fun initMapSettings() {
        naverMap.locationSource = locationSource
        naverMap.locationTrackingMode = LocationTrackingMode.Follow

        naverMap.addOnCameraIdleListener {
            naverMap.locationTrackingMode = LocationTrackingMode.Follow
        }
    }

    private fun checkPermissions() {
        val locationPermission = ContextCompat.checkSelfPermission(context!!, Manifest.permission.ACCESS_FINE_LOCATION)
        if(locationPermission == PackageManager.PERMISSION_GRANTED) {
            locationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10f, this)
            Toast.makeText(context, "위치 추적이 활성화됩니다.", Toast.LENGTH_LONG).show()
        } else {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            Toast.makeText(context, "설정에서 위치 서비스를 활성화할 수 있습니다.", Toast.LENGTH_LONG).show()
        }
    }

    private fun loadBikeList() {
        //Retrofit 사용
        val retrofit = Retrofit.Builder()
            .baseUrl(SeoulOpenApi.DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val seoulOpenService = retrofit.create(SeoulOpenService::class.java)
        //1 ~ 1000
        seoulOpenService.getBike(SeoulOpenApi.API_KEY1).enqueue(object : Callback<Bike> {
            override fun onResponse(call: Call<Bike>, response: Response<Bike>) {
                showBikeList(response.body() as Bike)
            }

            override fun onFailure(call: Call<Bike>, t: Throwable) {
                Toast.makeText(context, "서버 오류가 발생하였습니다.", Toast.LENGTH_LONG).show()
            }
        })
        //1001 ~ 2000
        seoulOpenService.getBike(SeoulOpenApi.API_KEY2).enqueue(object : Callback<Bike> {
            override fun onResponse(call: Call<Bike>, response: Response<Bike>) {
                showBikeList(response.body() as Bike)
            }

            override fun onFailure(call: Call<Bike>, t: Throwable) {
                Toast.makeText(context, "서버 오류가 발생하였습니다.", Toast.LENGTH_LONG).show()
            }
        })
        //2001 ~ 3000
        seoulOpenService.getBike(SeoulOpenApi.API_KEY3).enqueue(object : Callback<Bike> {
            override fun onResponse(call: Call<Bike>, response: Response<Bike>) {
                showBikeList(response.body() as Bike)
            }

            override fun onFailure(call: Call<Bike>, t: Throwable) {
                Toast.makeText(context, "서버 오류가 발생하였습니다.", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun showBikeList(bike: Bike) {
        var distance = 0.01f
        var curInfo = InfoWindow()
        //현재 위치와 자전거 대여소의 위치를 비교하여 내 위치 주변 대여소만 보여준다.
        for(b in bike.rentBikeStatus.row) {
            if(ContextCompat.checkSelfPermission(context!!, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                if(latitude-distance <= b.stationLatitude.toDouble() && b.stationLatitude.toDouble() <= latitude+distance
                    && longitude-distance <= b.stationLongitude.toDouble() && b.stationLongitude.toDouble() <= longitude+distance) {
                    val pos = LatLng(b.stationLatitude.toDouble(), b.stationLongitude.toDouble())
                    val infoWindow = InfoWindow()
                    infoWindow.adapter = object : InfoWindow.DefaultTextAdapter(context!!) {
                        override fun getText(infoWindow: InfoWindow): CharSequence {
                            return "자전거 : ${b.parkingBikeTotCnt} \n주차가능 : ${b.rackTotCnt}"
                        }
                    }
                    val listener = Overlay.OnClickListener { overlay ->
                        val marker = overlay as Marker
                        if (marker.infoWindow == null) {
                            curInfo.close()
                            // 현재 마커에 정보 창이 열려있지 않을 경우 엶
                            val cameraUpdate = CameraUpdate.scrollTo(pos)
                                .animate(CameraAnimation.Fly, 1000)
                            naverMap.moveCamera(cameraUpdate)
                            infoWindow.open(marker)
                            curInfo = infoWindow
                        } else {
                            curInfo.close()
                            // 이미 현재 마커에 정보 창이 열려있을 경우 닫음
                            infoWindow.close()
                        }
                        naverMap.setOnMapClickListener { pointF, latLng ->
                            infoWindow.close()
                        }
                        true
                    }

                    val marker = Marker()
                    marker.apply {
                        width = 80
                        height = 100
                        position = pos
                        map = naverMap
                        onClickListener = listener
                    }
                }
            } else {
                val pos = LatLng(b.stationLatitude.toDouble(), b.stationLongitude.toDouble())
                val infoWindow = InfoWindow()
                infoWindow.adapter = object : InfoWindow.DefaultTextAdapter(context!!) {
                    override fun getText(infoWindow: InfoWindow): CharSequence {
                        return "자전거 : ${b.parkingBikeTotCnt} \n주차가능 : ${b.rackTotCnt}"
                    }
                }
                val listener = Overlay.OnClickListener { overlay ->
                    val marker = overlay as Marker
                    if (marker.infoWindow == null) {
                        curInfo.close()
                        // 현재 마커에 정보 창이 열려있지 않을 경우 엶
                        val cameraUpdate = CameraUpdate.scrollTo(pos)
                            .animate(CameraAnimation.Fly, 1000)
                        naverMap.moveCamera(cameraUpdate)
                        infoWindow.open(marker)
                        curInfo = infoWindow
                    } else {
                        curInfo.close()
                        // 이미 현재 마커에 정보 창이 열려있을 경우 닫음
                        infoWindow.close()
                    }
                    naverMap.setOnMapClickListener { pointF, latLng ->
                        infoWindow.close()
                    }
                    true
                }

                val marker = Marker()
                marker.apply {
                    width = 80
                    height = 100
                    position = pos
                    map = naverMap
                    onClickListener = listener
                }
            }

        }
        val infoWindow = InfoWindow()
        naverMap.setOnMapClickListener { pointF, latLng ->
            infoWindow.close()
        }
    }

    override fun onLocationChanged(location: Location?) {
        if(locationManager == null) {
            return
        }
        //현재 위치를 저장하는 변수에 값을 할당한다.
        longitude = location!!.longitude
        latitude = location!!.latitude
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

    }

    override fun onProviderEnabled(provider: String?) {

    }

    override fun onProviderDisabled(provider: String?) {

    }
}