package com.goldcompany.apps.koreabike.db.history_address

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "user_address", primaryKeys = ["latitude", "longitude"])
class UserHistoryAddress(
    @ColumnInfo var date: Long = System.currentTimeMillis(),
    @ColumnInfo var latitude: Double,
    @ColumnInfo var longitude: Double,
    @ColumnInfo var address: String,
    @ColumnInfo var keyword: String,
    @ColumnInfo(defaultValue = "0") var selected: Boolean
)