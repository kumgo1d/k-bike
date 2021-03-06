package com.goldcompany.apps.koreabike.seoul

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.bike_bottom_sheet.ShowBikeDataBottomSheet
import com.goldcompany.apps.koreabike.databinding.FragmentBikeMapBinding
import com.goldcompany.apps.koreabike.home.HomeFragmentDirections
import com.goldcompany.apps.koreabike.location.LocationProvider
import com.goldcompany.apps.koreabike.seoul_bike_data.SeoulBike
import com.goldcompany.apps.koreabike.seoul_bike_data.StationInfo
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.util.MarkerIcons
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.NullPointerException


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

    var longitude: Double? = null
    var latitude: Double? = null

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bike_map, container, false)

        viewModel = ViewModelProvider(this, SeoulMapViewModelFactory()).get(
            SeoulMapViewModel::class.java
        )

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

        val mapFragment = childFragmentManager.findFragmentById(R.id.map)
                as MapFragment? ?: MapFragment.newInstance().also {
            childFragmentManager.beginTransaction().add(R.id.map, it).commit()
        }
        mapFragment.getMapAsync(this)

        addListener()

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
            setCameraPositionToMyLocation()
        }

        if(locationPermission == PackageManager.PERMISSION_DENIED){
            Toast.makeText(context, R.string.go_set_location, Toast.LENGTH_SHORT).show()
        }
    }

    private fun addListener() {
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)

        binding.searchAddress.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchAddressFragment())
        }

        binding.searchAddressButton.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchAddressFragment())
        }

        binding.myLocation.setOnClickListener {
            if(locationPermission == PackageManager.PERMISSION_GRANTED) {
                setCameraPositionToMyLocation()
            }
            else {
                checkPermissions()
            }
        }

        binding.pharmacy.setOnClickListener {
            setCategoryMarker("PM9", longitude.toString(), latitude.toString(), R.drawable.ic_location_pharmacy)
        }

        binding.convenienceStore.setOnClickListener {
            setCategoryMarker("CS2", longitude.toString(), latitude.toString(), R.drawable.ic_location_convenience_store)
        }

        binding.cafe.setOnClickListener {
            setCategoryMarker("CE7", longitude.toString(), latitude.toString(), R.drawable.ic_location_cafe)
        }

        binding.accommodation.setOnClickListener {
            setCategoryMarker("AD5", longitude.toString(), latitude.toString(), R.drawable.ic_location_accommodation)
        }
    }

    private fun setCameraPositionToMyLocation() {
        naverMap.locationSource = locationSource
        naverMap.locationTrackingMode = LocationTrackingMode.Follow
    }

    var isMakred = false
    var markerList = mutableListOf<Marker>()
    private fun setCategoryMarker(code: String, longitude: String, latitude: String, resource: Int) {
        viewModel.getItem(code, longitude, latitude).observe(viewLifecycleOwner) {
            for(i in it.documents.indices) {
                if(isMakred) {
                    markerList[0].map = null
                    markerList.removeAt(0)
                }
                else {
                    val marker = Marker()

                    marker.apply {
                        icon = OverlayImage.fromResource(resource)
                        width = 100
                        height = 100
                        position = LatLng(it.documents[i].y.toDouble(), it.documents[i].x.toDouble())
                        map = naverMap
                    }
                    markerList.add(marker)
                }
            }
            isMakred = !isMakred
        }
    }

    private fun initMapSettings() {
        naverMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_BICYCLE, true)
        naverMap.uiSettings.isZoomControlEnabled = false
        naverMap.minZoom = 13.0
    }

    @Override
    override fun onMapReady(naverMap: NaverMap) {
        isFirst = false
        this.naverMap = naverMap

        setCameraPosition()
        
        //TODO 만약 서울 API에서 NULL이 넘어왔을 경우 리스너 호출 X, Dialog 생성
        naverMap.addOnCameraChangeListener { _, _ ->
            lifecycleScope.launch {
                try {
                    showBikeList(bike1)
                    showBikeList(bike2)
                    showBikeList(bike3)
                } catch (e: NullPointerException) {
                    Timber.i("서울 api -> null")
                }
            }
        }
    }

    private fun setCameraPosition() {
        lifecycleScope.launch {
            val addressList = LocationProvider.getUserAddress()

            latitude = addressList?.latitude ?: 37.5643
            longitude = addressList?.longitude ?: 126.9801

            if(locationPermission == PackageManager.PERMISSION_GRANTED) {
                setCameraPositionToMyLocation()
            } else if(locationPermission == PackageManager.PERMISSION_GRANTED && addressList != null) {
                val cameraPosition = CameraPosition(LatLng(latitude!!, longitude!!), 15.0)

                naverMap.cameraPosition = cameraPosition
                setUserLocationMarker(latitude!!, longitude!!)
                initMapSettings()
            } else {
                val cameraPosition = CameraPosition(LatLng(latitude!!, longitude!!), 15.0)
                setUserLocationMarker(latitude!!, longitude!!)
                naverMap.cameraPosition = cameraPosition
                naverMap.locationTrackingMode = LocationTrackingMode.None
                initMapSettings()
            }
        }
    }

    private fun setUserLocationMarker(latitude: Double, longitude: Double) {
        val marker = Marker()
        marker.position = LatLng(latitude, longitude)
        marker.icon = MarkerIcons.BLUE
        marker.map = naverMap
    }

    private fun checkPermissions() {
        requestPermissions(
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_REQUEST_CODE
        )
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