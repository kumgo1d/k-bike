package com.goldcompany.apps.koreabike.favorite_list

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteListItem::class], version = 2, exportSchema = false)
abstract class FavoriteListItemHelper : RoomDatabase() {
    abstract fun FavoriteListItemDAO(): FavoriteListItemDAO
}