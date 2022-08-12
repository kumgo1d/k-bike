package com.goldcompany.koreabike.data.db

import androidx.room.*

@Dao
interface AddressDAO {
    @Query("select * from user_address ORDER BY date DESC")
    suspend fun getAll(): List<AddressEntity>

    @Query("select * from user_address WHERE selected = 1 LIMIT 1")
    suspend fun getAddress(): AddressEntity?

    @Query("UPDATE user_address SET selected = 0 WHERE id = :id")
    suspend fun updateCurrentAddressUnselected(id: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: AddressEntity)

    @Delete
    suspend fun delete(item: AddressEntity)
}