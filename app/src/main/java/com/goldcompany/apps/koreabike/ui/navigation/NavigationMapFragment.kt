package com.goldcompany.apps.koreabike.ui.navigation

import android.graphics.Color
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.goldcompany.apps.koreabike.MainActivity
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.databinding.FragmentNavigationMapBinding
import com.naver.maps.geometry.LatLng
import com.naver.maps.geometry.LatLngBounds
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.PathOverlay
import timber.log.Timber

class NavigationMapFragment : Fragment(), OnMapReadyCallback {
    private lateinit var binding: FragmentNavigationMapBinding
    private lateinit var naverMap: NaverMap

    private fun startMap() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.navigation_map_container)
                as MapFragment? ?: MapFragment.newInstance().also {
            childFragmentManager.beginTransaction().replace(R.id.navigation_map_container, it).commit()
        }
        mapFragment.getMapAsync(this)
    }

    private fun navigationPath() {
        val overlay = PathOverlay()
        val arg = arguments?.getParcelableArrayList<Parcelable>("path") as ArrayList<List<Double>>
        val path = mutableListOf<LatLng>()

        arg.forEachIndexed { index, _ ->
            path.add(LatLng(arg[index][1], arg[index][0]))
        }

        overlay.apply {
            coords = path
            color = Color.GREEN
            width = 10
            map = naverMap
        }

        val bounds = LatLngBounds(path[0], path[path.size-1])
        val camera = CameraUpdate.fitBounds(bounds, 300)
        naverMap.moveCamera(camera)
    }

    private fun setListener() {
        binding.navigationMapAppBar.navigationBackButton.setOnClickListener {
            findNavController().popBackStack()
        }

        val distance = "거리 : ${arguments?.getInt("distance")?.toLong()?.div(1000)}km"
        val duration = "소요시간 : 약 ${arguments?.getInt("duration")?.toLong()?.div(1000 * 60)?.plus(20)}분"
        binding.navDistance.text = distance
        binding.navDuration.text = duration
    }

    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap

        navigationPath()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_navigation_map, container, false)
        startMap()
        setListener()

        return binding.root
    }
}