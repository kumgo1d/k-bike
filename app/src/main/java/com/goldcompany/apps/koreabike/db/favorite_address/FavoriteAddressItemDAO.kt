package com.goldcompany.apps.koreabike.db.favorite_address

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface FavoriteAddressItemDAO {
    @Query("select * from list_item ORDER BY date DESC")
    suspend fun getAll(): MutableList<FavoriteAddressItem>

    @Insert(onConflict = REPLACE)
    suspend fun insert(item: FavoriteAddressItem)

    @Delete
    suspend fun delete(item: FavoriteAddressItem)
}