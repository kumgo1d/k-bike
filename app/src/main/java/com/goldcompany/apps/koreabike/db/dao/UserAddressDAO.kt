package com.goldcompany.apps.koreabike.db.dao

import androidx.room.*
import com.goldcompany.apps.koreabike.db.item.UserAddress
import kotlinx.coroutines.flow.Flow

@Dao
interface UserAddressDAO {
    @Query("select * from user_address ORDER BY date DESC")
    fun getAll(): Flow<List<UserAddress>>

    @Query("select * from user_address WHERE selected = 1 LIMIT 1")
    fun getAddress(): Flow<UserAddress?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: UserAddress)

    @Delete
    suspend fun delete(item: UserAddress)
}