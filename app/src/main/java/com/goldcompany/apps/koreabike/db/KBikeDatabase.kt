package com.goldcompany.apps.koreabike.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.goldcompany.apps.koreabike.db.dao.FavoriteListItemDAO
import com.goldcompany.apps.koreabike.db.dao.UserAddressDAO
import com.goldcompany.apps.koreabike.db.item.FavoriteListItem
import com.goldcompany.apps.koreabike.db.item.UserAddress

@Database(entities = [FavoriteListItem::class, UserAddress::class], version = 3, exportSchema = false)
abstract class KBikeDatabase : RoomDatabase() {
    abstract fun FavoriteListItemDAO(): FavoriteListItemDAO

    abstract fun UserAddressDAO(): UserAddressDAO
}