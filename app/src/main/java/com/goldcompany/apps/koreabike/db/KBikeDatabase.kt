package com.goldcompany.apps.koreabike.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.goldcompany.apps.koreabike.db.dao.FavoriteListItemDAO
import com.goldcompany.apps.koreabike.db.item.FavoriteListItem

@Database(entities = [FavoriteListItem::class], version = 2, exportSchema = false)
abstract class KBikeDatabase : RoomDatabase() {
    abstract fun FavoriteListItemDAO(): FavoriteListItemDAO
}