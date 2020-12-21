package com.android.apps.seoulpublicbike

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.room.Room
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_show_bike_data.view.*

class ShowBikeDataBottomSheet : BottomSheetDialogFragment() {
    var helper: FavoriteListItemHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        helper = Room.databaseBuilder(requireContext(), FavoriteListItemHelper::class.java, "favorite_list")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_show_bike_data, container, false)

        val station = requireArguments().getString("station_name")
        val parking = requireArguments().getString("parking_bike")
        val rack = requireArguments().getString("rack_bike")
        val no = station!!.split(".")[0].toLong()
        val adapter = FavoriteListAdapter()

        view.station_name.text = station
        view.parking_bike.text = "자전거 : " + parking
        view.rack_bike.text = "주차 가능 : " + rack
        view.add_favorite_button.setOnClickListener {
            val item = FavoriteListItem(no, station!!, parking!!, rack!!)
            helper?.FavoriteListItemDAO()?.insert(item)
            adapter.listItem.clear()
            adapter.listItem.addAll(helper?.FavoriteListItemDAO()?.getAll() ?: mutableListOf())
            adapter.notifyDataSetChanged()

            dismiss()
        }

        return view
    }
}