package com.goldcompany.apps.koreabike.db.history_address

import androidx.room.*

@Dao
interface UserHistoryAddressDAO {
    @Query("select * from user_address ORDER BY date DESC")
    suspend fun getAll(): MutableList<UserHistoryAddress>

    @Query("select * from user_address WHERE selected = 1 LIMIT 1")
    suspend fun getAddress(): UserHistoryAddress

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: UserHistoryAddress)

    @Delete
    suspend fun delete(item: UserHistoryAddress)
}