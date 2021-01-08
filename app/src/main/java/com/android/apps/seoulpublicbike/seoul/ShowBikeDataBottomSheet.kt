package com.android.apps.seoulpublicbike.seoul

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.android.apps.seoulpublicbike.databinding.FragmentShowBikeDataBinding
import com.android.apps.seoulpublicbike.favoritelist.FavoriteListAdapter
import com.android.apps.seoulpublicbike.favoritelist.FavoriteListItem
import com.android.apps.seoulpublicbike.favoritelist.FavoriteListItemHelper
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ShowBikeDataBottomSheet : BottomSheetDialogFragment() {

    private var _binding: FragmentShowBikeDataBinding? = null
    private val binding get() = _binding!!

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

        _binding = FragmentShowBikeDataBinding.inflate(inflater, container, false)

        val station = requireArguments().getString("station_name")
        val parking = requireArguments().getString("parking_bike")
        val rack = requireArguments().getString("rack_bike")
        val no = station!!.split(".")[0].toLong()
        val adapter = FavoriteListAdapter()

        binding.apply {
            stationName.text = station
            parkingBike.text = "자전거 : $parking"
            rackBike.text = "주차 가능 : $rack"
            binding.addFavoriteButton.setOnClickListener {
                val item = FavoriteListItem(no, station!!, parking!!, rack!!)
                helper?.FavoriteListItemDAO()?.insert(item)
                adapter.listItem.clear()
                adapter.listItem.addAll(helper?.FavoriteListItemDAO()?.getAll() ?: mutableListOf())
                adapter.notifyDataSetChanged()

                dismiss()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}