package com.goldcompany.apps.koreabike.db.item

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "list_item")
class FavoriteListItem(
    no: Long,
    @ColumnInfo var station: String,
    @ColumnInfo var parkingBike: String,
    @ColumnInfo var rackBike: String
) {
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo
    var no: Long? = no
}