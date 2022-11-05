package com.goldcompany.apps.koreabike.ui.bike_map

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.databinding.FragmentBikeMapBinding
import com.goldcompany.koreabike.domain.model.address.Address
import com.google.android.material.snackbar.Snackbar
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.util.MarkerIcons
import dagger.hilt.android.AndroidEntryPoint

interface BikeMapHandler {
    fun setCategoryMarkOverlay(code: String)
    fun checkPermissionAndGetMyLocation()
}

@AndroidEntryPoint
class BikeMapFragment : Fragment(), OnMapReadyCallback {

    companion object {
        private const val MARKER_WIDTH = 70
        private const val MARKER_HEIGHT = 100
        private const val PHARMACY = "PM9"
        private const val CONVENIENCE_STORE = "CS2"
        private const val CAFE = "CE7"
        private const val ACCOMMODATION = "AD5"

        const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }

    private lateinit var locationSource: FusedLocationSource
    private lateinit var binding: FragmentBikeMapBinding
    private lateinit var naverMap: NaverMap

    private val locationMarker = Marker()
    private val placeMarkers = mutableListOf<Marker>()
    private val viewModel by viewModels<BikeMapViewModel>()
    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Snackbar.make(binding.root, R.string.enable_location_service, Snackbar.LENGTH_SHORT).show()
            } else {
                Snackbar.make(binding.root, R.string.go_set_location, Snackbar.LENGTH_SHORT).show()
            }
        }

    private val handler = object : BikeMapHandler {
        override fun setCategoryMarkOverlay(code: String) {
            val latitude = naverMap.cameraPosition.target.latitude.toString()
            val longitude = naverMap.cameraPosition.target.longitude.toString()

            viewModel.searchNearbyPlacesMarker(code, longitude, latitude)
        }

        override fun checkPermissionAndGetMyLocation() {
            if (checkLocationPermission()) {
                locationMarker.map = null
                setCameraPositionToMyLocation()
            } else {
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    private fun setCameraPositionToMyLocation() {
        naverMap.locationSource = locationSource
        naverMap.locationTrackingMode = LocationTrackingMode.Follow
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        binding = FragmentBikeMapBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.handler = handler

        startMap()

        return binding.root
    }

    private fun startMap() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map)
                as MapFragment? ?: MapFragment.newInstance().also {
            childFragmentManager.beginTransaction().replace(R.id.map, it).commit()
        }
        mapFragment.getMapAsync(this)

        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeCurrentAddress()
        observeMarkerAddress()
        setUpSearchNavigation()
        setUpSearchAddress()
    }

    private fun observeCurrentAddress() {
//        viewModel.currentAddress.observe(viewLifecycleOwner) { address ->
//            setCameraPosition(address)
//        }
    }

    private fun setCameraPosition(address: Address?) {
        val latitude = address?.y?.toDouble() ?: 37.5643
        val longitude = address?.x?.toDouble() ?: 126.9801

        if (checkLocationPermission() && address != null) {
            val cameraPosition = CameraPosition(LatLng(latitude, longitude), 15.0)

            naverMap.cameraPosition = cameraPosition
            setUserLocationMarker(latitude, longitude)

        } else if (checkLocationPermission() && address == null) {
            setCameraPositionToMyLocation()
        } else {
            val cameraPosition = CameraPosition(LatLng(latitude, longitude), 15.0)
            setUserLocationMarker(latitude, longitude)
            naverMap.cameraPosition = cameraPosition
            naverMap.locationTrackingMode = LocationTrackingMode.None
        }
    }

    private fun observeMarkerAddress() {
        viewModel.markerAddress.observe(viewLifecycleOwner) { result ->
            clearPlaceMarker()
            result.forEach { place ->
                val marker = Marker()
                marker.apply {
                    width = MARKER_WIDTH
                    height = MARKER_HEIGHT
                    position = LatLng(place.y.toDouble(), place.x.toDouble())

                    when (viewModel.markerCode.value) {
                        PHARMACY -> {
                            marker.icon = MarkerIcons.BLACK
                            marker.iconTintColor = Color.RED
                        }
                        CONVENIENCE_STORE -> {
                            marker.icon = MarkerIcons.BLACK
                            marker.iconTintColor = Color.GREEN
                        }
                        CAFE -> {
                            marker.icon = MarkerIcons.BLACK
                            marker.iconTintColor = Color.DKGRAY
                        }
                        ACCOMMODATION -> {
                            marker.icon = MarkerIcons.BLACK
                            marker.iconTintColor = Color.MAGENTA
                        }
                    }
                }
                placeMarkers.add(marker)
                marker.map = naverMap
            }
        }
    }

    private fun clearPlaceMarker() {
        placeMarkers.forEach {
            it.map = null
        }
        placeMarkers.clear()
    }

    private fun setUpSearchNavigation() {
        binding.searchNavigationPathButton.setOnClickListener {
            val direction = BikeMapFragmentDirections.actionMapViewToNavigationFragment()
            findNavController().navigate(direction)
        }
    }

    private fun setUpSearchAddress() {
        binding.searchAddressButton.setOnClickListener {
            val direction = BikeMapFragmentDirections.actionMapViewToSearchAddressFragment()
            findNavController().navigate(direction)
        }
    }

    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap

        initMapSettings()
        getAddress()
    }

    private fun initMapSettings() {
        naverMap.apply {
            setLayerGroupEnabled(NaverMap.LAYER_GROUP_BICYCLE, true)
            uiSettings.isZoomControlEnabled = false
            minZoom = 13.0
        }
        naverMap.locationSource = locationSource
    }

    private fun getAddress() {
//        viewModel.getAddress()
    }

    private fun checkLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireActivity(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun setUserLocationMarker(latitude: Double, longitude: Double) {
        locationMarker.apply {
            position = LatLng(latitude, longitude)
            icon = MarkerIcons.BLACK
            iconTintColor = resources.getColor(R.color.colorPrimary, resources.newTheme())
            map = naverMap
        }
    }
}