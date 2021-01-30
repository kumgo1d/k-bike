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
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.bikebottomsheet.ShowBikeDataBottomSheet
import com.goldcompany.apps.koreabike.seoulbikedata.Bike
import com.goldcompany.apps.koreabike.databinding.FragmentBikeMapBinding
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay
import com.naver.maps.map.util.FusedLocationSource


class SeoulBikeMapFragment : Fragment(), OnMapReadyCallback {
    private lateinit var locationSource: FusedLocationSource
    private lateinit var naverMap: NaverMap
    private lateinit var seoulMapViewModel: SeoulMapViewModel

    var bike1: Bike? = null
    var bike2: Bike? = null
    var bike3: Bike? = null

    //내 위치
    private var longitude: Double = 0.0
    private var latitude: Double = 0.0

    //화면 안에 마커가 한번만 표시되기 위한, 화면 밖에 마커를 제거하기 위한 set
    private var bikeList = mutableSetOf<String>()
    private var isFirst = true

    private var _binding: FragmentBikeMapBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBikeMapBinding.inflate(inflater, container, false)

        seoulMapViewModel = ViewModelProvider(this, SeoulMapViewModelFactory()).get(
            SeoulMapViewModel::class.java)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map)
                as MapFragment? ?: MapFragment.newInstance().also {
            childFragmentManager.beginTransaction().add(R.id.map, it).commit()
        }
        mapFragment.getMapAsync(this)

        return binding.root
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(!isFirst && ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            initMapSettings()
        }

        if(ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED){
            Toast.makeText(context, "설정에서 위치 서비스를 활성화할 수 있습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()
        checkPermissions()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @Override
    override fun onMapReady(naverMap: NaverMap) {
        isFirst = false
        this.naverMap = naverMap

        if(ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            val cameraPosition = CameraPosition(LatLng(latitude, longitude), 15.0)
            naverMap.cameraPosition = cameraPosition
            initMapSettings()
        } else {
            val cameraPosition = CameraPosition(LatLng(37.5643, 126.9801), 15.0)
            naverMap.cameraPosition = cameraPosition
            naverMap.locationTrackingMode = LocationTrackingMode.None
        }

        seoulMapViewModel.getBikes1()!!.observe(this, { bike ->
            bike1 = bike
            showBikeList(bike)
        })
        seoulMapViewModel.getBikes2()!!.observe(this, { bike ->
            bike2 = bike
            showBikeList(bike)
        })
        seoulMapViewModel.getBikes3()!!.observe(this, { bike ->
            bike3 = bike
            showBikeList(bike)
        })

        naverMap.addOnCameraChangeListener { _, _ ->
            if(bike1 != null && bike2 != null && bike3 != null) {
                showBikeList(bike1!!)
                showBikeList(bike2!!)
                showBikeList(bike3!!)
            }
        }
    }

    private fun initMapSettings() {
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)

        naverMap.locationSource = locationSource
        naverMap.locationTrackingMode = LocationTrackingMode.Follow
        naverMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_BICYCLE, true)

        naverMap.addOnCameraIdleListener {
            naverMap.locationTrackingMode = LocationTrackingMode.Follow
        }
    }

    private fun checkPermissions() {
        val locationPermission = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        )

        requestPermissions(
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_REQUEST_CODE
        )

        if(locationPermission == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(context, "위치 추적이 활성화됩니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showBikeList(bike: Bike) {
        //현재 위치와 자전거 대여소의 위치를 비교하여 내 위치 주변 대여소만 보여준다.
        for(b in bike.getStationListHist.row) {
            if(!bikeList.contains(b.stationId)) {
                if(naverMap.contentBounds.southLatitude <= b.stationLatitude.toDouble() &&
                    b.stationLatitude.toDouble() <= naverMap.contentBounds.northLatitude &&
                    naverMap.contentBounds.westLongitude <= b.stationLongitude.toDouble() &&
                    b.stationLongitude.toDouble() <= naverMap.contentBounds.eastLongitude) {

                    bikeList.add(b.stationId)
                    val pos = LatLng(b.stationLatitude.toDouble(), b.stationLongitude.toDouble())

                    val listener = Overlay.OnClickListener { overlay ->
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
}