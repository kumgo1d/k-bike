package com.goldcompany.apps.koreabike.seoul

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.bikebottomsheet.ShowBikeDataBottomSheet
import com.goldcompany.apps.koreabike.seoulbikedata.SeoulBike
import com.goldcompany.apps.koreabike.databinding.FragmentBikeMapBinding
import com.goldcompany.apps.koreabike.seoulbikedata.StationInfo
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay
import com.naver.maps.map.util.FusedLocationSource
import kotlinx.coroutines.launch


class SeoulBikeMapFragment : Fragment(), OnMapReadyCallback {
    private lateinit var locationSource: FusedLocationSource
    private lateinit var viewModel: SeoulMapViewModel
    private lateinit var binding: FragmentBikeMapBinding

    private lateinit var naverMap: NaverMap

    private lateinit var bike1: SeoulBike
    private lateinit var bike2: SeoulBike
    private lateinit var bike3: SeoulBike

    //화면 안에 마커가 한번만 표시되기 위한, 화면 밖에 마커를 제거하기 위한 set
    private var bikeList = mutableSetOf<String>()
    private var isFirst = true

    private var locationPermission: Int? = null

    companion object {
        const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }

    override fun onStart() {
        super.onStart()

        locationPermission = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        )

        checkPermissions()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBikeMapBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this, SeoulMapViewModelFactory()).get(
            SeoulMapViewModel::class.java)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map)
                as MapFragment? ?: MapFragment.newInstance().also {
            childFragmentManager.beginTransaction().add(R.id.map, it).commit()
        }
        mapFragment.getMapAsync(this)

        lifecycleScope.launch {
            viewModel.getBikes1().observe(viewLifecycleOwner, { bike ->
                bike1 = bike
            })
            viewModel.getBikes2().observe(viewLifecycleOwner, { bike ->
                bike2 = bike
            })
            viewModel.getBikes3().observe(viewLifecycleOwner, { bike ->
                bike3 = bike
            })
        }

        return binding.root
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(!isFirst && locationPermission == PackageManager.PERMISSION_GRANTED) {
            initMapSettings()
        }

        if(locationPermission == PackageManager.PERMISSION_DENIED){
            Toast.makeText(context, R.string.go_set_location, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initMapSettings() {
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)

        naverMap.locationSource = locationSource
        naverMap.locationTrackingMode = LocationTrackingMode.Follow
        naverMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_BICYCLE, true)
        naverMap.uiSettings.isZoomControlEnabled = false

        naverMap.addOnCameraIdleListener {
            naverMap.locationTrackingMode = LocationTrackingMode.Follow
        }
    }

    @Override
    override fun onMapReady(naverMap: NaverMap) {
        isFirst = false
        this.naverMap = naverMap

        setCameraPosition()

        naverMap.addOnCameraChangeListener { _, _ ->
            showBikeList(bike1)
            showBikeList(bike2)
            showBikeList(bike3)
        }
    }

    private fun setCameraPosition() {
        if(locationPermission == PackageManager.PERMISSION_GRANTED) {
            val cameraPosition = CameraPosition(LatLng(37.5643, 126.9801), 15.0)
            naverMap.cameraPosition = cameraPosition
            initMapSettings()
        } else {
            val cameraPosition = CameraPosition(LatLng(37.5643, 126.9801), 15.0)
            naverMap.cameraPosition = cameraPosition
            naverMap.locationTrackingMode = LocationTrackingMode.None
        }
    }

    private fun checkPermissions() {
        requestPermissions(
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_REQUEST_CODE
        )

        if(locationPermission == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(context, R.string.enable_location_service, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showBikeList(bike: SeoulBike) {
        //현재 위치와 자전거 대여소의 위치를 비교하여 내 위치 주변 대여소만 보여준다.
        for(b in bike.stationList.stationInfo) {
            if(!bikeList.contains(b.stationId)) {
                if(naverMap.contentBounds.southLatitude <= b.stationLatitude.toDouble() &&
                    b.stationLatitude.toDouble() <= naverMap.contentBounds.northLatitude &&
                    naverMap.contentBounds.westLongitude <= b.stationLongitude.toDouble() &&
                    b.stationLongitude.toDouble() <= naverMap.contentBounds.eastLongitude) {

                    bikeList.add(b.stationId)
                    val pos = LatLng(b.stationLatitude.toDouble(), b.stationLongitude.toDouble())

                    val listener = markerListener(b, pos)

                    val marker = Marker()
                    marker.apply {
                        width = 80
                        height = 100
                        position = pos
                        map = naverMap
                        onClickListener = listener
                    }
                }
                else {
                    bikeList.remove(b.stationId)
                }
            }
        }
    }

    private fun markerListener(b: StationInfo, pos: LatLng): Overlay.OnClickListener {
        return Overlay.OnClickListener { overlay ->
            val marker = overlay as Marker
            if (marker.infoWindow == null) {
                val cameraUpdate = CameraUpdate.scrollTo(pos)
                    .animate(CameraAnimation.Fly, 1000)
                naverMap.moveCamera(cameraUpdate)

                val bottomSheet = ShowBikeDataBottomSheet()
                val bundle = Bundle()
                bundle.apply {
                    putString("station_name", b.stationName)
                    putString("parking_bike", b.parkingBikeTotCnt)
                    putString("rack_bike", b.rackTotCnt)
                }
                bottomSheet.arguments = bundle
                bottomSheet.show(childFragmentManager, bottomSheet.tag)
            }
            true
        }
    }
}