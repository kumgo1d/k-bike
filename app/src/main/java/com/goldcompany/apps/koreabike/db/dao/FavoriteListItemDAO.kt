package com.goldcompany.apps.koreabike.db.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.goldcompany.apps.koreabike.db.item.FavoriteListItem

@Dao
interface FavoriteListItemDAO {
    @Query("select * from list_item ORDER BY date DESC")
    suspend fun getAll(): MutableList<FavoriteListItem>

    @Insert(onConflict = REPLACE)
    suspend fun insert(item: FavoriteListItem)

    @Delete
    suspend fun delete(item: FavoriteListItem)
}