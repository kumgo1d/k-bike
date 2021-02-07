package com.goldcompany.apps.koreabike.search_address

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.databinding.FragmentSearchAddressBinding
import com.goldcompany.apps.koreabike.find_places.FindPlaces
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.LocationTrackingMode

class SearchAddressFragment : Fragment() {
    private lateinit var binding: FragmentSearchAddressBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_address, container, false)



        return binding.root
    }

//    private fun addListener() {
//        binding.searchAddressButton.setOnClickListener {
//            val inputMethodManager: InputMethodManager =
//                requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
//            inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus!!.windowToken, 0)
//
//            val address = binding.searchAddress.text.toString()
//
//            FindPlaces().callKakaoKeyword(address) { kakao, _ ->
//                if(kakao == null) {
//                    return@callKakaoKeyword
//                }
//
//                val latitude = kakao.documents[0].y.toDouble()
//                val longitude = kakao.documents[0].x.toDouble()
//                val cameraPosition = CameraPosition(LatLng(latitude, longitude), 15.0)
//                naverMap.cameraPosition = cameraPosition
//                naverMap.locationTrackingMode = LocationTrackingMode.NoFollow
//            }
//        }
//    }
}