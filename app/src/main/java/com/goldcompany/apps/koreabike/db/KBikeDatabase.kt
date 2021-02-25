package com.goldcompany.apps.koreabike.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.goldcompany.apps.koreabike.db.dao.FavoriteListItemDAO
import com.goldcompany.apps.koreabike.db.dao.UserAddressDAO
import com.goldcompany.apps.koreabike.db.item.FavoriteListItem
import com.goldcompany.apps.koreabike.db.item.UserAddress

@Database(entities = [FavoriteListItem::class, UserAddress::class], version = 3, exportSchema = false)
abstract class KBikeDatabase : RoomDatabase() {
    abstract fun FavoriteListItemDAO(): FavoriteListItemDAO

    abstract fun UserAddressDAO(): UserAddressDAO

    companion object {

        fun getInstance(context: Context): KBikeDatabase {
            return buildDatabaase(context)
        }

        private fun buildDatabaase(context: Context): KBikeDatabase {
            return Room.databaseBuilder(context, KBikeDatabase::class.java, "kbike_database")
                .build()
        }
    }
}