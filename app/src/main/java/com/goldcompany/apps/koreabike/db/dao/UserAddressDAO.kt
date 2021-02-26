package com.goldcompany.apps.koreabike.db.dao

import androidx.room.*
import com.goldcompany.apps.koreabike.db.item.UserAddress

@Dao
interface UserAddressDAO {
    @Query("select * from user_address ORDER BY date DESC")
    suspend fun getAll(): MutableList<UserAddress>

    @Query("select * from user_address WHERE selected = 1 LIMIT 1")
    suspend fun getAddress(): List<UserAddress>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: UserAddress)

    @Delete
    fun delete(item: UserAddress)
}