package com.android.apps.seoulpublicbike

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "list_item")
class FavoriteListItem {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var no: Long? = null
    @ColumnInfo
    var station: String = ""
    @ColumnInfo
    var parkingBike: String = ""
    @ColumnInfo
    var rackBike: String = ""

    constructor(station: String, parkingBike: String, rackBike: String) {
        this.station = station
        this.parkingBike = parkingBike
        this.rackBike = rackBike
    }
}