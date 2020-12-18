package com.android.apps.seoulpublicbike

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface FavoriteListItemDAO {
    @Query("select * from list_item")
    fun getAll(): MutableList<FavoriteListItem>

    @Insert(onConflict = REPLACE)
    fun insert(item: FavoriteListItem)

    @Delete
    fun delete(item: FavoriteListItem)
}