package com.goldcompany.apps.koreabike.seoul

import android.Manifest
import android.content.pm.PackageManager
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

    //카테고리 검색 중복 제거
    private var isMarked = false
    private var markerList = mutableListOf<Marker>()

    companion object {
        const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        checkPermissions()

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bike_map, container, false)

        viewModel = ViewModelProvider(this).get(SeoulMapViewModel::class.java)

        viewModel.getBikes1().observe(viewLifecycleOwner, { bike ->
            bike1 = bike
        })
        viewModel.getBikes2().observe(viewLifecycleOwner, { bike ->
            bike2 = bike
        })
        viewModel.getBikes3().observe(viewLifecycleOwner, { bike ->
            bike3 = bike
        })

        val mapFragment = childFragmentManager.findFragmentById(R.id.map)
                as MapFragment? ?: MapFragment.newInstance().also {
            childFragmentManager.beginTransaction().replace(R.id.map, it).commit()
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

        if(ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_DENIED){
            Toast.makeText(context, R.string.go_set_location, Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkPermissions() {
        requestPermissions(
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_REQUEST_CODE
        )
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
            if(ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED) {
                setCameraPositionToMyLocation()
            }
            else {
                checkPermissions()
            }
        }

        binding.pharmacy.setOnClickListener {
            setCategoryMarker("PM9", R.drawable.ic_location_pharmacy)
        }

        binding.convenienceStore.setOnClickListener {
            setCategoryMarker("CS2", R.drawable.ic_location_convenience_store)
        }

        binding.cafe.setOnClickListener {
            setCategoryMarker("CE7", R.drawable.ic_location_cafe)
        }

        binding.accommodation.setOnClickListener {
            setCategoryMarker("AD5", R.drawable.ic_location_accommodation)
        }
    }

    private fun setCameraPositionToMyLocation() {
        naverMap.locationSource = locationSource
        naverMap.locationTrackingMode = LocationTrackingMode.Follow
    }

    private fun setCategoryMarker(code: String, resource: Int) {
        val latitude = naverMap.cameraPosition.target.latitude.toString()
        val longitude = naverMap.cameraPosition.target.longitude.toString()

        viewModel.getItem(code, longitude, latitude).observe(viewLifecycleOwner) {
            for(i in it.documents.indices) {
                if(isMarked) {
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
            isMarked = !isMarked
        }
    }

    private fun initMapSettings() {
        naverMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_BICYCLE, true)
        naverMap.uiSettings.isZoomControlEnabled = false
        naverMap.minZoom = 13.0
    }

    @Override
    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap
        isFirst = false

        setCameraPosition()

        naverMap.addOnCameraChangeListener { _, _ ->
            lifecycleScope.launch {
                try {
                    showBikeList(bike1)
                    showBikeList(bike2)
                    showBikeList(bike3)
                } catch (e: UninitializedPropertyAccessException) {
                    Timber.i("서울 api -> null")
                }
            }
        }
    }

    private fun setCameraPosition() {
        lifecycleScope.launch {
            val address = LocationProvider.getUserAddress()

            val latitude = address?.latitude ?: 37.5643
            val longitude = address?.longitude ?: 126.9801

            initMapSettings()

            if(ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED && address != null) {

                val cameraPosition = CameraPosition(LatLng(latitude, longitude), 15.0)

                naverMap.cameraPosition = cameraPosition
                setUserLocationMarker(latitude, longitude)

            } else if(ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED && address == null) {

                setCameraPositionToMyLocation()

            } else {
                val cameraPosition = CameraPosition(LatLng(latitude, longitude), 15.0)
                setUserLocationMarker(latitude, longitude)
                naverMap.cameraPosition = cameraPosition
                naverMap.locationTrackingMode = LocationTrackingMode.None
            }
        }
    }

    private fun setUserLocationMarker(latitude: Double, longitude: Double) {
        val marker = Marker()
        marker.position = LatLng(latitude, longitude)
        marker.icon = MarkerIcons.BLUE
        marker.map = naverMap
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

                val bottomSheet = ShowBikeDataBottomSheet(
                    station = b.stationName,
                    parkingToCnt = b.parkingBikeTotCnt,
                    rackToCnt = b.rackTotCnt)
                bottomSheet.show(childFragmentManager, bottomSheet.tag)
            }
            true
        }
    }
}