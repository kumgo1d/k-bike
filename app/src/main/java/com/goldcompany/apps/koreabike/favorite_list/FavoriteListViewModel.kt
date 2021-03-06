package com.goldcompany.apps.koreabike.favorite_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goldcompany.apps.koreabike.KBikeApplication
import com.goldcompany.apps.koreabike.db.item.FavoriteListItem
import com.goldcompany.apps.koreabike.seoul.seoul_api.SeoulRepository
import com.goldcompany.apps.koreabike.seoul_bike_data.SeoulBike
import kotlinx.coroutines.launch

class FavoriteListViewModel: ViewModel() {
    private var list = MutableLiveData<MutableList<FavoriteListItem>>()

    private var bikes1 = MutableLiveData<SeoulBike>()
    private var bikes2 = MutableLiveData<SeoulBike>()
    private var bikes3 = MutableLiveData<SeoulBike>()

    fun getBikes1(): LiveData<SeoulBike> {
        bikes1 = SeoulRepository.getBikeData1()
        return bikes1
    }

    fun getBikes2(): LiveData<SeoulBike> {
        bikes2 = SeoulRepository.getBikeData2()
        return bikes2
    }

    fun getBikes3(): LiveData<SeoulBike> {
        bikes3 = SeoulRepository.getBikeData3()
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