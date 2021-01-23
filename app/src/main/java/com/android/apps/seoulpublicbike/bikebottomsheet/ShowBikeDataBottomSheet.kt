package com.android.apps.seoulpublicbike.bikebottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.room.Room
import com.android.apps.seoulpublicbike.databinding.FragmentShowBikeDataBinding
import com.android.apps.seoulpublicbike.favoritelist.FavoriteListAdapter
import com.android.apps.seoulpublicbike.favoritelist.FavoriteListItem
import com.android.apps.seoulpublicbike.favoritelist.FavoriteListItemHelper
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ShowBikeDataBottomSheet : BottomSheetDialogFragment() {

    private var _binding: FragmentShowBikeDataBinding? = null
    private val binding get() = _binding!!
    private val viewModel = ShowBikeDataBottomSheetViewModel()

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

        val station = requireArguments().getString("station_name")
        val parking = "자전거 : " + requireArguments().getString("parking_bike")
        val rack = "주차가능 : " + requireArguments().getString("rack_bike")
        val no = station!!.split(".")[0].toLong()
        val bikeData = BottomSheetBikeData(no, station, parking!!, rack!!)

        _binding = FragmentShowBikeDataBinding.inflate(inflater, container, false)

        viewModel.bottomSheetBikeData(bikeData)

        binding.viewModel = viewModel
        binding.addFavoriteButton.setOnClickListener {
            addFavoriteButton(bikeData)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addFavoriteButton(bikeData: BottomSheetBikeData) {
        val adapter = FavoriteListAdapter()
        val item = FavoriteListItem(bikeData.stationNumber, bikeData.stationName, bikeData.parkingBike, bikeData.rackBike)

        helper?.FavoriteListItemDAO()?.insert(item)

        adapter.listItem.clear()
        adapter.listItem.addAll(helper?.FavoriteListItemDAO()?.getAll() ?: mutableListOf())
        adapter.notifyDataSetChanged()

        dismiss()

        Toast.makeText(context, "즐겨찾기에 추가되었습니다.", Toast.LENGTH_SHORT).show()
    }
}