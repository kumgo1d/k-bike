package com.goldcompany.apps.koreabike.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.goldcompany.apps.koreabike.db.dao.FavoriteListItemDAO
import com.goldcompany.apps.koreabike.db.dao.UserAddressDAO
import com.goldcompany.apps.koreabike.db.item.FavoriteListItem
import com.goldcompany.apps.koreabike.db.item.UserAddress

@Database(entities = [FavoriteListItem::class, UserAddress::class], version = 4, exportSchema = false)
abstract class KBikeDatabase : RoomDatabase() {
    abstract fun FavoriteListItemDAO(): FavoriteListItemDAO

    abstract fun UserAddressDAO(): UserAddressDAO

    companion object {

        fun getInstance(context: Context): KBikeDatabase {
            return buildDatabase(context)
        }

        private val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE `user_Address` ADD COLUMN `keyword` TEXT NOT NULL")
            }
        }

        private fun buildDatabase(context: Context): KBikeDatabase {
            return Room.databaseBuilder(context, KBikeDatabase::class.java, "kbike_database")
                .addMigrations(MIGRATION_3_4)
                .build()
        }
    }
}