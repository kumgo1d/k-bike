package com.goldcompany.apps.koreabike.ui.bike_map

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.databinding.FragmentBikeMapBinding
import com.goldcompany.apps.koreabike.location.LocationProvider
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.util.MarkerIcons
import kotlinx.coroutines.launch


class BikeMapFragment : Fragment(), OnMapReadyCallback {
    private lateinit var locationSource: FusedLocationSource
    private lateinit var viewModel: BikeMapViewModel
    private lateinit var binding: FragmentBikeMapBinding
    private lateinit var naverMap: NaverMap

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
        onCheckPermissions()

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bike_map, container, false)

        viewModel = ViewModelProvider(this).get(BikeMapViewModel::class.java)

        startMap()
        addListener()

        return binding.root
    }

    private fun onCheckPermissions() {
        if(ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(context, R.string.go_set_location, Toast.LENGTH_SHORT).show()

                ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            } else {
                ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            }
        }
    }

    private fun startMap() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map)
                as MapFragment? ?: MapFragment.newInstance().also {
            childFragmentManager.beginTransaction().replace(R.id.map, it).commit()
        }
        mapFragment.getMapAsync(this)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
//        if(ActivityCompat.checkSelfPermission(
//                requireContext(),
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) == PackageManager.PERMISSION_DENIED){
//            Toast.makeText(context, R.string.go_set_location, Toast.LENGTH_SHORT).show()
//        }

        when(requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(context, "권한이 설정되었습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "권한이 설정되지 않았습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun checkPermissions() {
        requestPermissions(
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    private fun setCameraPositionToMyLocation() {
        naverMap.locationSource = locationSource
        naverMap.locationTrackingMode = LocationTrackingMode.Follow
    }

    private fun addListener() {
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)

        binding.searchAddress.setOnClickListener {
            findNavController().navigate(BikeMapFragmentDirections.actionMapViewToSearchAddressFragment())
        }

        binding.searchAddressButton.setOnClickListener {
            findNavController().navigate(BikeMapFragmentDirections.actionMapViewToSearchAddressFragment())
        }

        binding.myLocation.setOnClickListener {
            if(ActivityCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED) {
                setCameraPositionToMyLocation()
            }
            else {
//                checkPermissions()
                onCheckPermissions()
            }
        }

        binding.pharmacy.setOnClickListener {
            setCategoryMarker("PM9")
        }

        binding.convenienceStore.setOnClickListener {
            setCategoryMarker("CS2")
        }

        binding.cafe.setOnClickListener {
            setCategoryMarker("CE7")
        }

        binding.accommodation.setOnClickListener {
            setCategoryMarker("AD5")
        }
    }

    private fun setCategoryMarker(code: String) {
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
                        width = 80
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

    private fun setUserLocationMarker(latitude: Double, longitude: Double) {
        val marker = Marker()
        marker.position = LatLng(latitude, longitude)
        marker.icon = MarkerIcons.BLUE
        marker.map = naverMap
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

    @Override
    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap

        setCameraPosition()
    }
}