package com.android.apps.seoulpublicbike

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.fragment_show_bike_data.*
import kotlinx.android.synthetic.main.fragment_show_bike_data.view.*

class ShowBikeDataBottomSheet : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_show_bike_data, container, false)

        view.station_name.text = arguments?.getString("station_name")
        view.parking_bike.text = "자전거 : " + arguments?.getString("parking_bike")
        view.rack_bike.text = "주차 가능 : " + arguments?.getString("rack_bike")
        // Inflate the layout for this fragment
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}