package com.goldcompany.apps.koreabike.bike_bottom_sheet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShowBikeDataBottomSheetViewModel: ViewModel() {
    private var bikeData = MutableLiveData<BottomSheetBikeData>()

    fun bottomSheetBikeData(data: BottomSheetBikeData) {
        bikeData.value = data
    }

    fun getBottomSheetBikeData(): LiveData<BottomSheetBikeData> {
        return bikeData
    }
}