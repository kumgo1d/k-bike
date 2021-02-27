package com.goldcompany.apps.koreabike.favorite_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goldcompany.apps.koreabike.KBikeApplication
import com.goldcompany.apps.koreabike.db.item.FavoriteListItem
import kotlinx.coroutines.launch

class FavoriteListViewModel: ViewModel() {
    private var list = MutableLiveData<List<FavoriteListItem>?>()

    fun getList(): LiveData<List<FavoriteListItem>?> {
        loadFavoriteList()
        return list
    }

    private fun loadFavoriteList() {
        viewModelScope.launch {
            list.value = KBikeApplication.instance.database.FavoriteListItemDAO().getAll()
        }
    }

    fun deleteListItem(item: FavoriteListItem) {
        viewModelScope.launch {
            KBikeApplication.instance.database.FavoriteListItemDAO().delete(item)
        }
    }
}