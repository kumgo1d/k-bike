package com.android.apps.seoulpublicbike

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface FavoriteListItemDAO {
    @Query("select * from list_item")
    fun getAll(): MutableList<FavoriteListItem>

    @Insert(onConflict = REPLACE)
    fun insert(item: FavoriteListItem)

    @Delete
    fun delete(item: FavoriteListItem)
}