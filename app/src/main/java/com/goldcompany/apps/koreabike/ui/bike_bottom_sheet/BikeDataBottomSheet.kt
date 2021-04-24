package com.goldcompany.apps.koreabike.ui.bike_bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.goldcompany.apps.koreabike.KBikeApplication
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.data.BottomSheetBikeData
import com.goldcompany.apps.koreabike.databinding.FragmentBikeBottomSheetItemBinding
import com.goldcompany.apps.koreabike.db.item.FavoriteListItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch

class BikeDataBottomSheet(
    private val station: String,
    private val parkingToCnt: String,
    private val rackToCnt: String
) : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentBikeBottomSheetItemBinding
    private lateinit var viewModel: BikeDataBottomSheetViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentBikeBottomSheetItemBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this).get(BikeDataBottomSheetViewModel::class.java)

        val parking = "자전거 : $parkingToCnt"
        val rack = "주차가능 : $rackToCnt"
        val no = station.split(".")[0].toLong()
        val bikeData = BottomSheetBikeData(no, station, parking, rack)

        viewModel.bottomSheetBikeData(bikeData)

        binding.viewModel = viewModel
        binding.addFavoriteButton.setOnClickListener {
            lifecycleScope.launch {
                addFavoriteButton(bikeData)
            }
        }

        return binding.root
    }

    private suspend fun addFavoriteButton(bikeData: BottomSheetBikeData) {
        val item = FavoriteListItem(bikeData.stationNumber, bikeData.stationName, bikeData.parkingBike, bikeData.rackBike)

        KBikeApplication.instance.database.FavoriteListItemDAO().insert(item)
        dismiss()

        Toast.makeText(context, R.string.add_favorite_item, Toast.LENGTH_SHORT).show()
    }
}