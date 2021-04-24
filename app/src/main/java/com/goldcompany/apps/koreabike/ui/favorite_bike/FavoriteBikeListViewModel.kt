package com.goldcompany.apps.koreabike.ui.favorite_bike

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goldcompany.apps.koreabike.KBikeApplication
import com.goldcompany.apps.koreabike.db.item.FavoriteListItem
import com.goldcompany.apps.koreabike.api.seoul_api.SeoulBikeRepository
import com.goldcompany.apps.koreabike.data.seoul.SeoulBike
import kotlinx.coroutines.launch

class FavoriteBikeListViewModel: ViewModel() {
    private var list = MutableLiveData<MutableList<FavoriteListItem>>()

    private var bikes1 = MutableLiveData<SeoulBike>()
    private var bikes2 = MutableLiveData<SeoulBike>()
    private var bikes3 = MutableLiveData<SeoulBike>()

    fun getBikes1(): LiveData<SeoulBike> {
        bikes1 = SeoulBikeRepository.getBikeData1()
        return bikes1
    }

    fun getBikes2(): LiveData<SeoulBike> {
        bikes2 = SeoulBikeRepository.getBikeData2()
        return bikes2
    }

    fun getBikes3(): LiveData<SeoulBike> {
        bikes3 = SeoulBikeRepository.getBikeData3()
        return bikes3
    }

    fun getList(): LiveData<MutableList<FavoriteListItem>> {
        loadFavoriteList()
        return list
    }

    private fun loadFavoriteList() {
        viewModelScope.launch {
            list.value = KBikeApplication.instance.database.FavoriteListItemDAO().getAll()
        }
    }

    fun insertItem(item: FavoriteListItem) {
        viewModelScope.launch {
            KBikeApplication.instance.database.FavoriteListItemDAO().insert(item)
        }
    }

    fun deleteListItem(item: FavoriteListItem) {
        viewModelScope.launch {
            KBikeApplication.instance.database.FavoriteListItemDAO().delete(item)
        }
    }
}