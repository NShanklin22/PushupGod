package com.example.roomdatabase

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pushupLogs")
class PushupLog {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "logId")
    var id: Int = 0

    @ColumnInfo(name = "date")
    var date: String = ""

    @ColumnInfo(name = "numPushed")
    var numPushed: Int = 0

    constructor() {}

    constructor(id: Int, date: String, numPushed: Int) {
        this.date = date
        this.numPushed = numPushed
    }
    constructor(date: String, numPushed: Int) {
        this.date = date
        this.numPushed = numPushed
    }

}