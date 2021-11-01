package com.goldcompany.apps.koreabike.ui.bike_map

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.goldcompany.apps.koreabike.MainActivity
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.databinding.FragmentBikeMapBinding
import com.goldcompany.apps.koreabike.location.LocationProvider
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.util.MarkerIcons
import kotlinx.coroutines.launch

interface BikeMapHandler {
    fun setCategoryMarkOverlay(code: String)
    fun checkPermissionAndGetMyLocation()
    fun goSearchFragment()
    fun goNavigationFragment()
}

class BikeMapFragment : Fragment(), OnMapReadyCallback {
    private lateinit var locationSource: FusedLocationSource
    private lateinit var viewModel: BikeMapViewModel
    private lateinit var binding: FragmentBikeMapBinding
    private lateinit var naverMap: NaverMap

    private var isNearbyPlaceOverlayMarked = false
    private var categoryMarkers = mutableListOf<Marker>()
    private var locationMarker = Marker()

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if(isGranted) {
                Toast.makeText(requireContext(), R.string.enable_location_service, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), R.string.go_set_location, Toast.LENGTH_SHORT).show()
            }
        }

    private val handler = object : BikeMapHandler {
        override fun setCategoryMarkOverlay(code: String) {
            val latitude = naverMap.cameraPosition.target.latitude.toString()
            val longitude = naverMap.cameraPosition.target.longitude.toString()

            viewModel.getItem(code, longitude, latitude).observe(viewLifecycleOwner) {
                for(i in it.documents.indices) {
                    if(isNearbyPlaceOverlayMarked) {
                        categoryMarkers[0].map = null
                        categoryMarkers.removeAt(0)
                    }
                    else {
                        val marker = Marker()

                        marker.apply {
                            width = 70
                            height = 100
                            position = LatLng(it.documents[i].y.toDouble(), it.documents[i].x.toDouble())
                            map = naverMap
                        }
                        categoryMarkers.add(marker)
                    }
                }
                isNearbyPlaceOverlayMarked = !isNearbyPlaceOverlayMarked
            }
        }

        override fun checkPermissionAndGetMyLocation() {
            if(checkLocationPermission()) {
                locationMarker.map = null
                setCameraPositionToMyLocation()
            } else {
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }

        override fun goSearchFragment() {
            findNavController().navigate(BikeMapFragmentDirections.actionMapViewToSearchAddressFragment())
        }

        override fun goNavigationFragment() {
            findNavController().navigate(BikeMapFragmentDirections.actionMapViewToNavigationFragment())
        }
    }

    companion object {
        const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bike_map, container, false)
        viewModel = ViewModelProvider(this).get(BikeMapViewModel::class.java)
        binding.viewModel = viewModel
        binding.handler = handler

        MainActivity.showBottom()
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)

        lifecycleScope.launch {
            startMap()
        }

        return binding.root
    }

    @Override
    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap

        initMapSettings()
        setCameraPosition()
    }

    private fun startMap() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map)
                as MapFragment? ?: MapFragment.newInstance().also {
            childFragmentManager.beginTransaction().replace(R.id.map, it).commit()
        }
        mapFragment.getMapAsync(this)

        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
    }

    private fun checkLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireActivity(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun setCameraPositionToMyLocation() {
        naverMap.locationSource = locationSource
        naverMap.locationTrackingMode = LocationTrackingMode.Follow
    }

    private fun setCameraPosition() {
        lifecycleScope.launch {
            val address = LocationProvider.getUserAddress()
            val latitude = address?.latitude ?: 37.5643
            val longitude = address?.longitude ?: 126.9801

            if(checkLocationPermission() && address != null) {
                val cameraPosition = CameraPosition(LatLng(latitude, longitude), 15.0)

                naverMap.cameraPosition = cameraPosition
                setUserLocationMarker(latitude, longitude)

            } else if(checkLocationPermission() && address == null) {
                setCameraPositionToMyLocation()
            } else {
                val cameraPosition = CameraPosition(LatLng(latitude, longitude), 15.0)
                setUserLocationMarker(latitude, longitude)
                naverMap.cameraPosition = cameraPosition
                naverMap.locationTrackingMode = LocationTrackingMode.None
            }
        }
    }

    private fun initMapSettings() {
        naverMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_BICYCLE, true)
        naverMap.uiSettings.isZoomControlEnabled = false
        naverMap.minZoom = 13.0
    }

    private fun setUserLocationMarker(latitude: Double, longitude: Double) {
        locationMarker.position = LatLng(latitude, longitude)
        locationMarker.icon = MarkerIcons.BLACK
        locationMarker.iconTintColor = resources.getColor(R.color.colorPrimary, resources.newTheme())
        locationMarker.map = naverMap
    }
}