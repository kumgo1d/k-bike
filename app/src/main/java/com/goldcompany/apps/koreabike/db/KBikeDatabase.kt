package com.goldcompany.apps.koreabike.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddressDAO
import com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress

@Database(entities = [UserHistoryAddress::class], version = 7, exportSchema = false)
abstract class KBikeDatabase : RoomDatabase() {
    abstract fun userAddressDAO(): UserHistoryAddressDAO

    companion object {
        fun getInstance(context: Context): KBikeDatabase {
            return buildDatabase(context)
        }

        private val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE `user_Address` ADD COLUMN `keyword` TEXT NOT NULL")
            }
        }

        private val MIGRATION_4_5 = object : Migration(4, 5) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE new_address (`date` INTEGER NOT NULL," +
                        " `latitude` REAL NOT NULL, `longitude` REAL NOT NULL," +
                        " `address` TEXT NOT NULL, `keyword` TEXT NOT NULL, `selected` INTEGER NOT NULL DEFAULT 0," +
                        " PRIMARY KEY(`latitude`, `longitude`))")

                database.execSQL("INSERT INTO new_address(" +
                        "`date`, `latitude`, `longitude`, `address`, `keyword`, `selected`) " +
                        "SELECT `date`, `latitude`, `longitude`, `address`, `keyword`, `selected` FROM user_address")

                database.execSQL("DROP TABLE user_address")
                database.execSQL("ALTER TABLE new_address RENAME TO user_address")
            }
        }

        private val MIGRATION_5_6 = object : Migration(5, 6) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("DROP TABLE list_item")
            }
        }

        private val MIGRATION_6_7 = object : Migration(6, 7) {
            override fun migrate(database: SupportSQLiteDatabase) {

            }
        }

        private fun buildDatabase(context: Context): KBikeDatabase {
            return Room.databaseBuilder(context, KBikeDatabase::class.java, "kbike_database")
                .addMigrations(MIGRATION_3_4, MIGRATION_4_5, MIGRATION_5_6, MIGRATION_6_7)
                .build()
        }
    }
}