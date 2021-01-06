package com.android.apps.seoulpublicbike.favoritelist

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(FavoriteListItem::class), version = 2, exportSchema = false)
abstract class FavoriteListItemHelper : RoomDatabase() {
    abstract fun FavoriteListItemDAO(): FavoriteListItemDAO
}