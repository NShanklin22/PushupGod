package com.example.pushupgod

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    // The Query is what will be used to access the database
    // the function name followed by : the data type returned
    @Query("SELECT * FROM pushupLogs ORDER BY date DESC")
    fun sortLogsByDateDesc(): List<PushupLog>

    @Query("SELECT * FROM pushupLogs ORDER BY date ASC")
    fun sortLogsByDateAsc(): List<PushupLog>

    @Query("SELECT * FROM pushupLogs WHERE date BETWEEN :StartDate AND :EndDate")
    fun getLogsBetweenDates(StartDate: String, EndDate: String): List<PushupLog>
}