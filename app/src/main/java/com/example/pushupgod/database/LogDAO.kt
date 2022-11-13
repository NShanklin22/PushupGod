package com.example.pushupgod

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pushupgod.database.PushupLog

@Dao
interface PushupLogDao {

    @Insert
    fun insertLog(pushupLog: PushupLog)

    @Query("SELECT * FROM pushupLogs WHERE date = :name")
    fun findLog(name: String): List<PushupLog>

    @Query("DELETE FROM pushupLogs WHERE date = :name")
    fun deleteLog(name: String)

    @Query("SELECT * FROM pushupLogs")
    fun getAllLogs(): LiveData<List<PushupLog>>

}