package com.android.apps.seoulpublicbike

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "list_item")
class FavoriteListItem {
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo
    var no: Long? = null
    @ColumnInfo
    var station: String = ""
    @ColumnInfo
    var parkingBike: String = ""
    @ColumnInfo
    var rackBike: String = ""

    constructor(no: Long, station: String, parkingBike: String, rackBike: String) {
        this.no = no
        this.station = station
        this.parkingBike = parkingBike
        this.rackBike = rackBike
    }
}