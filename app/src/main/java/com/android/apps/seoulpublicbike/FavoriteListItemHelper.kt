package com.android.apps.seoulpublicbike

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(FavoriteListItem::class), version = 1, exportSchema = false)
abstract class FavoriteListItemHelper : RoomDatabase() {
    abstract fun FavoriteListItemDAO(): FavoriteListItemDAO
}