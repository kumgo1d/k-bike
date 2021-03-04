package com.goldcompany.apps.koreabike.db.item

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "user_address", primaryKeys = ["latitude", "longitude"])
class UserAddress(
    @ColumnInfo var date: Long = Date().time,
    @ColumnInfo var latitude: Double,
    @ColumnInfo var longitude: Double,
    @ColumnInfo var address: String,
    @ColumnInfo var keyword: String,
    @ColumnInfo(defaultValue = "0") var selected: Boolean
)