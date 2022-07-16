package com.goldcompany.apps.koreabike.db.history_address

import androidx.room.*

@Dao
interface UserHistoryAddressDAO {
    @Query("select * from user_address ORDER BY date DESC")
    suspend fun getAll(): MutableList<UserHistoryAddress>

    @Query("select * from user_address WHERE selected = 1 LIMIT 1")
    suspend fun getAddress(): UserHistoryAddress?

    @Query("UPDATE user_address SET selected = 0 WHERE date = :date")
    suspend fun updateAddressUnselect(date: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: UserHistoryAddress)

    @Delete
    suspend fun delete(item: UserHistoryAddress)
}